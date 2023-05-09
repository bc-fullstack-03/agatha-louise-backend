package com.sysmap.parrot.services.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private AwsService awsService;

	@Override
	public String upload(MultipartFile file, String fileName) {

		String fileUri = "";

		try {
			fileUri = awsService.upload(file, fileName);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return fileUri;
	}
}
