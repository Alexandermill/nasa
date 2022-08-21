package com.example.testWeb;

import com.example.testWeb.config.ManifestDeserializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ManifestDeserializer.class)
public class Manifest {

    String name;

//    @JsonProperty("max_sol")
    private int maxSol;

//    @JsonProperty("max_date")
    private String earthDate;

    private List<String> sol = new ArrayList<>();

    public Manifest() {
    }

    public Manifest(String name, int maxSol, String earthDate, List<String> sol) {
        this.name = name;
        this.maxSol = maxSol;
        this.earthDate = earthDate;
        this.sol = sol;
    }
    
    public List<String> getLostSols(){
        int lastSol = Integer.parseInt(sol.get(sol.size()-1));
        List<String> lostSol = new ArrayList<>();
        for (int i = 0; i < lastSol; i++) {
            if(!sol.contains(String.valueOf(i))){
                lostSol.add(String.valueOf(i));
            }
        }
        return lostSol;
    }


}
