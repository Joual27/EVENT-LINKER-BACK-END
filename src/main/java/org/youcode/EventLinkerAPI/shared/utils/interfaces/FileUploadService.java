package org.youcode.EventLinkerAPI.shared.utils.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadImage(MultipartFile file);
}
