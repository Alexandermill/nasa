package com.example.testWeb.clients;

import com.example.testWeb.Entity.Photos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient (name = "roverclient", url = "https://api.nasa.gov/mars-photos/api/v1/rovers/")
public interface RoverClient {

    @RequestMapping(method = RequestMethod.GET, value = "{rover}/photos", consumes = MediaType.APPLICATION_JSON_VALUE)
    Photos getPhotosByCamera(@PathVariable("rover") String rover, @RequestParam("sol") int sol, @RequestParam("camera") String cam, @RequestParam("api_key") String apiKey);

}
