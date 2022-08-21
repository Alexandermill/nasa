package com.example.testWeb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photos {
    List<Photo> photos;

    public Photos() {
    }

    public Photos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Image> getImage (Photos photos){
        List<Image> images = new ArrayList<>();
        for(Photo photo : photos.getPhotos()){
            images.add(new Image(photo.getImg_src()));
        }
        return images;
    }

}
