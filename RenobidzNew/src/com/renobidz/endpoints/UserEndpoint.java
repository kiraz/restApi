package com.renobidz.endpoints;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.renobidz.common.utils.EmailProperties;
import com.renobidz.common.utils.MD5PasswordGenerator;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.RandomGenerator;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;
import com.renobidz.common.utils.SessionValues;
import com.renobidz.common.utils.UserEmailService;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.dao.UserDAO;
import com.renobidz.store.entity.User;

/**
 * @author Ankur
 * 
 * User Cloud Endpoints
 *
 */
@Api(name = "userEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class UserEndpoint extends AbstractEndpoint<UserDAO>{
	private static final Logger logger = Logger.getLogger(UserEndpoint.class.getName());
	
	@Override
	protected UserDAO getDAO() {
		return UserDAO.getInstance();
	}


    /**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * create new user 
	 */
	@ApiMethod(name = Path.OperationUrl.CREATE, path = Path.OperationUrl.CREATE, httpMethod = HttpMethod.POST)
	public Response create(User user) throws DatabaseException, IOException {
		logger.info("creating the user entity");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser == null){
			// Encoding password before saving to the datastore
			if(user.getIsSocialUser() == null || user.getIsSocialUser() == false){
				String encodedPassword = MD5PasswordGenerator.getEncodedPassword(user.getPassword());
				user.setCreationDate(System.currentTimeMillis());
                user.setIncorrectPwd(0);
                user.setPassword(encodedPassword);
				user.setIsLocked(true);
                user.setIsCompany(false);
				user.setPasswordResetKey(RandomGenerator.randomUniqueGenerator());
			}
			Long id = getDAO().createWithID(user);
			String activationUrl = EmailProperties.siteUrl + "activate?id=" + id+"&key="+user.getPasswordResetKey();
			logger.info("ACTIVATION URL : "+activationUrl);
			UserEmailService.selectAndSendEmail(user, activationUrl, "Welcome to Renobidz.com", EmailProperties.emailTemplate_verify_user);

            return new Response(STATUS.SUCCESS, "Thank you for signing in. An email was sent to complete the sign in process.");
		}else{
			return new Response(STATUS.FAILURE, "This email address already exists.");
		}
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * check user login 
	 */
	@ApiMethod(name = Path.Url.LOGIN, path = Path.Url.LOGIN, httpMethod = HttpMethod.POST)
	public Response login(User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("checking user login");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			if(isExistingUser.getIsSocialUser() != null && isExistingUser.getIsSocialUser()){
			    //placeholder for socialuser login process
				return new Response(STATUS.FAILURE, "User email is registered with "+isExistingUser.getSocialwebsite()+". Please login with your "+isExistingUser.getSocialwebsite()+" account");
			}else if(isExistingUser.getIsLocked()){
				return new Response(STATUS.FAILURE, "This user is currently locked for security purposes. Please reset your password.");
			}else{
				String encodedPassword = MD5PasswordGenerator.getEncodedPassword(user.getPassword());
				String dbPassword = isExistingUser.getPassword();
				if(dbPassword.equals(encodedPassword)){
					SessionValues.getInstance().setUser(request, isExistingUser);
                    /*HttpSession session = request.getSession();
                    session.setAttribute("user", user);*/
                    //System.out.println("Session ID: " + session.getId());
					return new Response(STATUS.SUCCESS, "User logged in successfully");
				}else{
                    //Increment number of unsuccessful login
                    //Lock account if incorrect login = 3
                    String returnMsg = "Incorrect email address or password. Please try again.";

                    int nbIncorrectPwd = isExistingUser.getIncorrectPwd();
                    nbIncorrectPwd = nbIncorrectPwd + 1;

                    isExistingUser.setIncorrectPwd(nbIncorrectPwd);

                    if (nbIncorrectPwd >= 3) {
                        //Locking account...
                        isExistingUser.setIsLocked(Boolean.TRUE);
                        returnMsg = "Incorrect email address or password. Account locked.";
                    }

                    getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
                    return new Response(STATUS.FAILURE, returnMsg);
				}
			}
		}else{
			return new Response(STATUS.FAILURE, "Incorrect email address or password. Please try again.");
		}
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * forget password 
	 */
	@ApiMethod(name = Path.Url.FORGET_PASSWORD, path = Path.Url.FORGET_PASSWORD, httpMethod = HttpMethod.POST)
	public Response forgetpassword(User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("forget password user login");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			if(isExistingUser.getIsSocialUser() != null && isExistingUser.getIsSocialUser()){
				return new Response(STATUS.FAILURE, "User email is registered with "+isExistingUser.getSocialwebsite()+". Please login with your "+isExistingUser.getSocialwebsite()+" account");
			}else if(isExistingUser.getIsLocked()){
				return new Response(STATUS.FAILURE, "User email is not verified");
			}else{
				isExistingUser.setPasswordResetKey(RandomGenerator.randomUniqueGenerator());
				getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
				String activationUrl = EmailProperties.siteUrl + "resetpassword?id=" + isExistingUser.getId()+"&key="+isExistingUser.getPasswordResetKey();
				logger.info("ACTIVATION URL : "+activationUrl);
				UserEmailService.selectAndSendEmail(isExistingUser, activationUrl, "Reset password to Renobidz.com", EmailProperties.emailTemplate_reset_password);
				return new Response(STATUS.SUCCESS, "An email has been sent with a link to "+isExistingUser.getEmail()+". Please click on the link to reset your password.");	
			}
		}else{
			return new Response(STATUS.FAILURE, "User email doesn't exists");
		}
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * change password 
	 */
	@ApiMethod(name = Path.Url.CHANGE_PASSWORD, path = Path.Url.CHANGE_PASSWORD, httpMethod = HttpMethod.POST)
	public Response changePassword(@Named("oldPassword") String oldPassword, User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("change password user login");
        logger.info(oldPassword);
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null) {
            if (isExistingUser.getIsSocialUser() != null && isExistingUser.getIsSocialUser()) {
                return new Response(STATUS.FAILURE, "User email is registered with " + isExistingUser.getSocialwebsite() + ". Please login with your " + isExistingUser.getSocialwebsite() + " account");
            } else if (isExistingUser.getIsLocked()) {
                return new Response(STATUS.FAILURE, "User email is not verified");
            } else {
                //Making sure old password matches current password
                String oldEncodedPassword = MD5PasswordGenerator.getEncodedPassword(oldPassword);
                String oldDBEncodedPassword = isExistingUser.getPassword();
                String newEncodedPassword = MD5PasswordGenerator.getEncodedPassword(user.getPassword());
                logger.info(oldDBEncodedPassword);
                logger.info(oldEncodedPassword);
                logger.info(newEncodedPassword);

                if (oldDBEncodedPassword.equals(oldEncodedPassword)) {
                    if (user.getPassword() != null && user.getPassword().length() > 5) {
                        if (oldEncodedPassword.equals(newEncodedPassword)) {
                            return new Response(STATUS.FAILURE, "Password cannot be same as your previous password.");
                        } else {
                            isExistingUser.setPassword(newEncodedPassword);
                            getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
                            return new Response(STATUS.SUCCESS, "Your password has been updated successfully.");
                        }
                    } else
                        return new Response(STATUS.FAILURE, "Password must be of 6 or more characters.");
                } else {
                    return new Response(STATUS.FAILURE, "Password incorrect.");
                }
            }
        } else{
            return new Response(STATUS.FAILURE, "User email doesn't exists");
        }
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * reset password 
	 */
	@ApiMethod(name = Path.Url.RESET_PASSWORD, path = Path.Url.RESET_PASSWORD, httpMethod = HttpMethod.POST)
	public Response resetpassword(User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("reset password user login");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			if(isExistingUser.getIsSocialUser() != null && isExistingUser.getIsSocialUser()){
				return new Response(STATUS.FAILURE, "User email is registered with "+isExistingUser.getSocialwebsite()+". Please login with your "+isExistingUser.getSocialwebsite()+" account");
			}else if(isExistingUser.getIsLocked()){
				return new Response(STATUS.FAILURE, "User email is not verified");
			}else{
				isExistingUser.setPassword(MD5PasswordGenerator.getEncodedPassword(user.getPassword()));
				isExistingUser.setPasswordResetKey(null);
				getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
				return new Response(STATUS.SUCCESS, "Password updated successfully");	
			}
		}else{
			return new Response(STATUS.FAILURE, "User email doesn't exists");
		}
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * change user profile pic 
	 */
	@ApiMethod(name = Path.Url.CHANGE_USER_PROFILE_PIC, path = Path.Url.CHANGE_USER_PROFILE_PIC, httpMethod = HttpMethod.POST)
	public Response changeUserProfilePic(User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("change user profile pic");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			// Deleting the previous image
			if(isExistingUser.getProfilePictureBlobKey() != null){
				BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
				blobstoreService.delete(new BlobKey(isExistingUser.getProfilePictureBlobKey()));
			}
			isExistingUser.setProfilePicture(user.getProfilePicture());
			isExistingUser.setProfilePictureBlobKey(user.getProfilePictureBlobKey());
			getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
			SessionValues.getInstance().setUser(request, isExistingUser);
			return new Response(STATUS.SUCCESS, "Profile pic updated successfully");
		}else{
			return new Response(STATUS.FAILURE, "User email doesn't exists");
		}
    }
	
	/**
	 * @param user
	 * @throws DatabaseException
	 * @throws IOException
	 * 
	 * change user profile pic 
	 */
	@ApiMethod(name = Path.Url.UPDATE_USER_PROFILE_INFO, path = Path.Url.UPDATE_USER_PROFILE_INFO, httpMethod = HttpMethod.POST)
	public Response updateUserProfileInfo(User user, HttpServletRequest request) throws DatabaseException, IOException {
		logger.info("update user profile info");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			isExistingUser.setFirstName(user.getFirstName());
			isExistingUser.setLastName(user.getLastName());
            isExistingUser.setPhoneNumber(user.getPhoneNumber());
            isExistingUser.setPrefLanguage(user.getPrefLanguage());
			getDAO().update(User.class, isExistingUser.getId(), isExistingUser);
			SessionValues.getInstance().setUser(request, isExistingUser);
			return new Response(STATUS.SUCCESS, "Profile info updated successfully");	
		}else{
			return new Response(STATUS.FAILURE, "User email doesn't exists");
		}
    }
	
	/**
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * 
	 * get the user entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.GET, path = Path.OperationUrl.GET, httpMethod = HttpMethod.GET)
    public User get(@Named("id") Long id) throws DatabaseException {
		logger.info("loading the user entity by id : "+id);
		return getDAO().getById(User.class, id);
    }
	
	/**
	 * @param user
	 * @return
	 * @throws DatabaseException
	 * 
	 * update the user entity
	 */
	@ApiMethod(name = Path.OperationUrl.UPDATE, path = Path.OperationUrl.UPDATE, httpMethod = HttpMethod.PUT)
	public Response updateUser(User user) throws DatabaseException {
		logger.info("updating the user entity");
		User isExistingUser = getDAO().getByEmail(user.getEmail());
		if(isExistingUser != null){
			//TODO: Didn't need to exposed directly
			//getDAO().update(User.class, user.getId(), user);
			return new Response(STATUS.SUCCESS, "User updated successfully");
		}else{
			return new Response(STATUS.FAILURE, "User doesn't exists");
		}
    }
	
	/**
	 * @param id
	 * @throws DatabaseException
	 * 
	 * delete the user entity by id
	 */
	@ApiMethod(name = Path.OperationUrl.DELETE, path = Path.OperationUrl.DELETE, httpMethod = HttpMethod.DELETE)
    public void delete(@Named("id") Long id) throws DatabaseException {
		logger.info("deleting the user entity by id : "+id);
		getDAO().delete(User.class, id);
    }
}
