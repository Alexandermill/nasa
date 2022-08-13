package com.example.testWeb.controllers;

import com.example.testWeb.Manifest;
import com.example.testWeb.clients.ManifestClient;
import com.example.testWeb.PhotoManifest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class restcontroller {

    @Autowired
    ManifestClient manifestClient;

    @GetMapping("/manifest")
    public Manifest getManifest(){
        Manifest manifest = manifestClient.getManifest();
        System.out.println(manifest.getMaxSol());
        return manifest;
    }

    @CrossOrigin
    @GetMapping("/manifest/lost")
    public List<String>  getLostSols(){
        Manifest manifest = manifestClient.getManifest();
        return manifest.getLostSols();
    }
}
