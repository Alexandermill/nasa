package com.example.testWeb.controllers;

import com.example.testWeb.Entity.Manifest;
import com.example.testWeb.clients.ManifestClient;
import com.example.testWeb.clients.RoverClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class control {

    @Autowired
    RoverClient roverClient;

    @Autowired
    ManifestClient manifestClient;

    @GetMapping
    public String getIndex(Model model){
        return "index";
    }

    @GetMapping(value = "/curiosity")
    public String getCuriosity(Model model){
        return "curiosity_test";
    }

    @GetMapping(value = "/spirit")
    public String getSpirit(Model model){
        return "spirit";
    }

    @GetMapping(value = "/opportunity")
    public String getOpportunity(Model model){
        return "opportunity";
    }

    @GetMapping(value = "/test")
    public String getText(Model model){
        return "test";
    }


}
