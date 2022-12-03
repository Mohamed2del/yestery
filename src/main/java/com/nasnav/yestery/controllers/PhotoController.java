package com.nasnav.yestery.controllers;

import com.nasnav.yestery.config.SecurityConfig;
import com.nasnav.yestery.dto.PhotoDto;
import com.nasnav.yestery.entities.Photo;
import com.nasnav.yestery.enums.Category;
import com.nasnav.yestery.enums.Role;
import com.nasnav.yestery.repository.PhotoRepository;
import com.nasnav.yestery.service.PhotoService;
import com.nasnav.yestery.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.nasnav.yestery.constant.Constant.UPLOAD_DIRECTORY;

@RestController
@Api(  tags="Photos APIs", description = "Provides Upload , view , approve and reject Photos Apis")
public class PhotoController {

    @Autowired
    PhotoService photoService;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    UserService userService;



    @PostMapping("/uploadPhoto")
    public Photo uploadPhoto( @RequestParam("photo") MultipartFile file,@RequestParam("description") String description, @RequestParam("category") int category ) throws IOException {
        Photo photo = new Photo();
        photoService.PersistPhoto(file,photo,description,category);
        return  photo;
    }

    @GetMapping("/photos")
    public ResponseEntity getPhotos(@RequestHeader Long userId){
        Role role = userService.getUserRole(userId);
        if(role.equals(Role.ADMIN))
            return ResponseEntity.ok(photoRepository.findAll());
        else
            return ResponseEntity.ok(photoRepository.getApprovedPhotos());
    }

    @PutMapping("/approve")
    public ResponseEntity approvePhoto(@RequestBody PhotoDto photoDto , @RequestHeader Long userId) throws Exception {
        Role role = userService.getUserRole(userId);
        if(!role.equals(Role.ADMIN))
            return ResponseEntity.status(401).build();
        Long photoId = photoDto.getPhotoId();
        photoService.approvePhoto(photoId);
        return ResponseEntity.ok("Photo has been approved successfully");
    }

    @PutMapping("/reject")
    public ResponseEntity rejectPhoto(@RequestBody PhotoDto photoDto , @RequestHeader Long userId) throws Exception {
        Role role = userService.getUserRole(userId);
        if(!role.equals(Role.ADMIN))
            return ResponseEntity.status(401).build();
            Long photoId = photoDto.getPhotoId();
        photoService.rejectPhoto(photoId);
        return ResponseEntity.ok("Photo has been rejected successfully");
    }



}
