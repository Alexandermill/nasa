package com.example.testWeb.clients;

import com.example.testWeb.Entity.Manifest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "manifestlient", url = "https://api.nasa.gov/mars-photos/api/v1/manifests/")
public interface ManifestClient {

    @Cacheable("manifest")
    @RequestMapping(method = RequestMethod.GET, value = "{rover}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Manifest getManifest(@PathVariable("rover") String rover, @RequestParam("api_key") String apiKey);



}
