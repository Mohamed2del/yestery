package com.nasnav.yestery.serviceImpl;

import com.nasnav.yestery.entities.Photo;
import com.nasnav.yestery.enums.Category;
import com.nasnav.yestery.enums.PhotoStatus;
import com.nasnav.yestery.repository.PhotoRepository;
import com.nasnav.yestery.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.nasnav.yestery.constant.Constant.UPLOAD_DIRECTORY;

@Component
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    private String StorePhoto( MultipartFile file ) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY,file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        return fileNameAndPath.toString();
    }

    @Override
    public Long PersistPhoto( MultipartFile file, Photo photo,String description ,int category ) throws IOException {
        photo.setCategory(Category.values()[category]);
        photo.setDescription(description);
        photo.setPath(UPLOAD_DIRECTORY+file.getOriginalFilename());
        photo.setStatus(PhotoStatus.PENDING_APPROVAL);
        Long photoID = photoRepository.save(photo).getId();
        StorePhoto(file);
        return photoID;
    }

    @Override
    public Long approvePhoto( Long photoId ) throws Exception {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        if(!photoOptional.isPresent())
            throw new Exception("Photo is not found");
        Photo photo = photoOptional.get();
        photo.setStatus(PhotoStatus.APPROVED);
        return photoRepository.save(photo).getId();
    }

    @Override
    public Long rejectPhoto( Long photoId ) throws Exception {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        if(!photoOptional.isPresent())
            throw new Exception("Photo is not found");
        Photo photo = photoOptional.get();
        photo.setStatus(PhotoStatus.REJECTED);
        return photoRepository.save(photo).getId();
    }
}
