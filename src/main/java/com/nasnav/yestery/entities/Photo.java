package com.nasnav.yestery.entities;

import com.nasnav.yestery.enums.Category;
import com.nasnav.yestery.enums.PhotoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String description;
    @Column(nullable = false, unique = true)
    private String path;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @Enumerated(EnumType.ORDINAL)
    private PhotoStatus status;

}
