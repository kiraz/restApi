package com.renobidz.endpoints;

import java.util.logging.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.renobidz.common.utils.Path;
import com.renobidz.common.utils.Response;
import com.renobidz.common.utils.STATUS;

/**
 * @author Ankur
 *
 * File Upload Endpoint
 * 
 */
@Api(name = "fileInfoEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.renobidz.com", ownerName = "endpoints.renobidz.com", packagePath=""))
public class FileInfoEndpoint{
	
	private static final Logger logger = Logger.getLogger(FileInfoEndpoint.class.getName());
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	/**
	 * @return
	 */
	@ApiMethod(name = Path.Url.FILE + Path.OperationUrl.UPLOAD + Path.OperationUrl.URL, path = Path.Url.FILE + Path.OperationUrl.UPLOAD + Path.OperationUrl.URL, httpMethod = HttpMethod.GET)
	public Response getFileUploadUrl(){
		logger.info("Generating url for file upload.");
		String url = blobstoreService.createUploadUrl("/file-upload");
		return new Response(STATUS.SUCCESS, url);
	}
	
	
	/**
	 * @param blobKey
	 * @return
	 */
	@ApiMethod(name = Path.Url.FILE + Path.OperationUrl.DELETE, path = Path.Url.FILE + Path.OperationUrl.DELETE, httpMethod = HttpMethod.GET)
	public Object delete(@Named("blobKey") String blobKey){
		try {
			if(blobKey != null){
				logger.info("deleting file with blobkey : "+blobKey);
				blobstoreService.delete(new BlobKey(blobKey));
				return new Response(STATUS.SUCCESS, "File deleted successfully");	
			}
		} catch (NumberFormatException e) {
			return new Response(STATUS.FAILURE, "Please pass a valid blobkey");
		} catch (Exception e) {
			return new Response(STATUS.FAILURE, "Some internal server error has occurred. Please try after sometimes");
		}
		return new Response(STATUS.FAILURE, "Some internal server error has occurred. Please try after sometimes");
	}
}
