package com.renobidz.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.gson.Gson;
import com.renobidz.endpoints.dto.FileInfo;

@SuppressWarnings("serial")
public class FileUpload extends HttpServlet{
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
		List<BlobKey> blobKeys = blobs.get("myFile[]");
		if (blobKeys != null && !blobKeys.isEmpty()){
			List<FileInfo> fileInfos = new ArrayList<FileInfo>(0);
			for(BlobKey blobKey : blobKeys){
				BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
				BlobInfo info = blobInfoFactory.loadBlobInfo(blobKey);
				FileInfo imageInfo = new FileInfo();
				imageInfo.setFileName(info.getFilename());
				if(info.getContentType().contains("image")){
					ImagesService services = ImagesServiceFactory.getImagesService();
					ServingUrlOptions serve = ServingUrlOptions.Builder.withBlobKey(blobKey);
					String imageUrl = services.getServingUrl(serve);
					imageInfo.setImageUrl(imageUrl);
				}else{
					imageInfo.setImageUrl(null);
				}
				imageInfo.setContentType(info.getContentType());
				imageInfo.setBlobkey(info.getBlobKey().getKeyString());
				long sizeInKB = info.getSize()/1000;
				String size = Long.toString(sizeInKB);
				imageInfo.setSize(size);
				fileInfos.add(imageInfo);
			}
			response.getWriter().print(toJsonArray(fileInfos));
		}
	}
	
	public String toJsonArray(List<FileInfo> fileInfos){
		Gson gson = new Gson();
		return gson.toJson(fileInfos);
	}
}
