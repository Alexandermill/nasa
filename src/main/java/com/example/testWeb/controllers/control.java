package com.example.testWeb.controllers;

import com.example.testWeb.Manifest;
import com.example.testWeb.clients.ManifestClient;
import com.example.testWeb.clients.RoverClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class control {

    @Autowired
    RoverClient roverClient;

    @Autowired
    ManifestClient manifestClient;

    @GetMapping
    public String getIndex(Model model){
        return "index2";
    }

    @GetMapping(value = "/curiosity")
    public String getInde(Model model){
        Manifest manifest = manifestClient.getManifest();
        model.addAttribute("manifest", manifest);
        return "curiosity";
    }

    @GetMapping(value = "/info")
    public String getIfo(Model model){
        Manifest manifest = manifestClient.getManifest();
        System.out.println(manifest.getMaxSol());
        model.addAttribute("manifest", manifest);
        return "info";
    }

    @PostMapping(value = "/photos")
    public String getPhotos(@RequestParam("camera") String cam, @RequestParam("sol") int sol, Model model){
        model.addAttribute("photos", roverClient.getPhotosByCamera(sol, cam, "N4nE7pvuZ2QfD0gsBQaOdm6SOIodp9KQaL0Rxclc").getPhotos());
        model.addAttribute("sol", sol);

        Manifest manifest = manifestClient.getManifest();
        System.out.println(manifest.getMaxSol());
        model.addAttribute("manifest", manifest);

        return "photo";

    }


}
