package com.example.testWeb.clients;

import com.example.testWeb.Manifest;
import com.example.testWeb.PhotoManifest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "manifestlient", url = "https://api.nasa.gov/mars-photos/api/v1/manifests/Curiosity?api_key=N4nE7pvuZ2QfD0gsBQaOdm6SOIodp9KQaL0Rxclc")
public interface ManifestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    Manifest getManifest();

}
