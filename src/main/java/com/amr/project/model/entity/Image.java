package com.amr.project.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.scribejava.core.base64.Base64;
import lombok.*;

import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiIgnore
@Builder
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] picture;

    @Column(name = "is_main")
    private Boolean isMain = false;

    @JsonIgnore
    @OneToOne(mappedBy = "images", cascade = {CascadeType.PERSIST})
    private User user;

    public String getPictureForPage() {
        if (picture == null) {
            return "https://img.etimg.com/thumb/msid-79733946,width-300,imgsize-300305,,resizemode-4,quality-100/cyber.jpg";
        }

        String str_ = "data:jpg;base64,";
        str_ += Base64.encode(picture);
        return str_;
    }
}
