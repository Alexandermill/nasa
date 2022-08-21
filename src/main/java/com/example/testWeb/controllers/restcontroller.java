package com.example.testWeb.controllers;

import com.example.testWeb.*;
import com.example.testWeb.clients.ManifestClient;
import com.example.testWeb.clients.RoverClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class restcontroller {

    @Autowired
    ManifestClient manifestClient;
    @Autowired
    RoverClient roverClient;

    @GetMapping("/manifest")
    public Manifest getManifest(){
        Manifest manifest = manifestClient.getManifest();
        return manifest;
    }

    @CrossOrigin
    @GetMapping("/manifest/lost")
    public List<String>  getLostSols(){
        Manifest manifest = manifestClient.getManifest();
        return manifest.getLostSols();
    }


    @CrossOrigin
    @PostMapping(value = "/data")
    public List<Image> getLostSols(@RequestParam("sol") int sol, @RequestParam("camera") String cam){
        Photos photos = (Photos) roverClient.getPhotosByCamera(sol, cam, "N4nE7pvuZ2QfD0gsBQaOdm6SOIodp9KQaL0Rxclc");
        return photos.getImage(photos);
    }

    @CacheEvict(allEntries = true, cacheNames = { "manifest"})
    @Scheduled(fixedRate = 9000000)
    public void cacheEvict() {
        System.out.println("Cache \"manifest\" evict");
    }
}
