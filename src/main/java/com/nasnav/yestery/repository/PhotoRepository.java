package com.nasnav.yestery.repository;

import com.nasnav.yestery.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p WHERE p.status = 1")
    List<Photo> getApprovedPhotos();
}
