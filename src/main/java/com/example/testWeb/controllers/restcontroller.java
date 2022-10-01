package com.example.testWeb.controllers;

import com.example.testWeb.Entity.Image;
import com.example.testWeb.Entity.Manifest;
import com.example.testWeb.Entity.Photos;
import com.example.testWeb.clients.ManifestClient;
import com.example.testWeb.clients.RoverClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class restcontroller {
    @Value("${api.key}")
    String apiKey;

    @Autowired
    ManifestClient manifestClient;
    @Autowired
    RoverClient roverClient;

    @CrossOrigin
    @GetMapping("/manifest/{rover}")
    public Manifest getManifest(@PathVariable("rover") String rover){
        Manifest manifest = manifestClient.getManifest(rover, apiKey);
        return manifest;
    }

    // @CrossOrigin
    // @GetMapping("/manifest/lost")
    // public List<String>  getLostSols(){
    //     Manifest manifest = manifestClient.getManifest();
    //     return manifest.getLostSols();
    // }

//    @CrossOrigin
//    @GetMapping("/manifest/sol")
//    public List<String>  getSols(){
//        Manifest manifest = manifestClient.getManifest();
//        return manifest.getSol();
//    }


    @CrossOrigin
    @PostMapping(value = "/data")
    public List<Image> getLostSols(@RequestParam("rover") String rover, @RequestParam("sol") int sol, @RequestParam("camera") String cam){
        Photos photos = (Photos) roverClient.getPhotosByCamera(rover, sol, cam, apiKey);
        return photos.getImage(photos);
    }

       

    @CacheEvict(allEntries = true, cacheNames = { "manifest"})
    @Scheduled(fixedRate = 9000000)
    public void cacheEvict() {
        System.out.println("Cache \"manifest\" evict");
    }
}
