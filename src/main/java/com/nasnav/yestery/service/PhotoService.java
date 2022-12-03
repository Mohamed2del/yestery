package com.nasnav.yestery.service;

import com.nasnav.yestery.dto.UserDto;
import com.nasnav.yestery.entities.Photo;
import com.nasnav.yestery.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {

    Long PersistPhoto(MultipartFile file, Photo photo,String description ,int category)throws IOException;

    Long approvePhoto(Long photoId) throws Exception;

    Long rejectPhoto(Long photoId) throws Exception;
}
