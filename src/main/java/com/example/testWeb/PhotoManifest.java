package com.example.testWeb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("photo_manifest")
public class PhotoManifest {

    @JsonUnwrapped
    Manifest manifest;

    public PhotoManifest() {
    }

    public PhotoManifest(Manifest manifest) {
        this.manifest = manifest;
    }
}
