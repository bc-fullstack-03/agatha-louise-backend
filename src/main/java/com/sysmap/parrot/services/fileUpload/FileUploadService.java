package com.sysmap.parrot.services.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String upload(MultipartFile file, String fileName);
}
