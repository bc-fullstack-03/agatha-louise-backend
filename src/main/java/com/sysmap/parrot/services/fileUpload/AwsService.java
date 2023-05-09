package com.sysmap.parrot.services.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface AwsService {
	String upload(MultipartFile multipartFile, String fileName) throws Exception;
}
