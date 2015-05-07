package com.renobidz.common.utils;

public interface Path {
	public interface Url {
		String LOGIN = "login";
		String CHANGE_PASSWORD = "changepassword";
		String FORGET_PASSWORD = "forgetpassword";
		String RESET_PASSWORD = "resetpassword";
		String CHANGE_USER_PROFILE_PIC = "changeUserProfilePic";
		String UPDATE_USER_PROFILE_INFO = "updateUserProfileInfo";
		String FILE = "file";
	}
	
	public interface OperationUrl {
		String CREATE = "create";
		String GET = "get";
		String LIST = "list";
		String UPDATE = "update";
		String DELETE = "delete";
		String UPLOAD = "upload";
		String DOWNLOAD = "download";
		String URL = "url";
		
		String DETAILS = "details";		
	}
}
