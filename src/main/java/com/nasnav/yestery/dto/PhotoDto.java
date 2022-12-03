package com.nasnav.yestery.dto;


import com.nasnav.yestery.enums.PhotoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {

    private Long photoId;
    private String description;
    private String path;
    private PhotoStatus status;
}
