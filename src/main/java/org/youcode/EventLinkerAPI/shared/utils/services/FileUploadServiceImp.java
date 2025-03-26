package org.youcode.EventLinkerAPI.shared.utils.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.youcode.EventLinkerAPI.exceptions.ImageUploadFailedException;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.FileUploadService;

import java.util.Map;

@AllArgsConstructor
@Service
public class FileUploadServiceImp implements FileUploadService {
    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try{
            byte[] fileBytes = file.getBytes();
            Map<? , ?> uploadResult = cloudinary.uploader()
                    .upload(fileBytes , ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        }catch (Exception e){
            throw new ImageUploadFailedException("failed uploading file :" + e.getMessage());
        }
    }
}
