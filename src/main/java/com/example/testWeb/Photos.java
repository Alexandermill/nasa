package com.example.testWeb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
