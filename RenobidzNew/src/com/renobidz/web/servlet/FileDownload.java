package com.renobidz.web.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.renobidz.common.utils.STATUS;

@SuppressWarnings("serial")
public class FileDownload extends HttpServlet{
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String blobKey = request.getParameter("blobKey");
		try {
			if(blobKey != null){
					BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
					BlobInfo blobInfo = null;
					blobInfo = blobInfoFactory.loadBlobInfo(new BlobKey(blobKey));
					String fileName = blobInfo.getFilename();
					response.setContentType(blobInfo.getContentType());
					response.setHeader("Content-Disposition", "attachment; filename=\"" +fileName +"\"");
					blobstoreService.serve(new BlobKey(blobKey), response);	
			}
		} catch (NumberFormatException e) {
			response.getWriter().println(STATUS.FAILURE.name() + " : Please pass a valid blobkey");
		} catch (Exception e) {
			response.getWriter().println(STATUS.FAILURE.name() + e.getMessage());
		}
	}
}
