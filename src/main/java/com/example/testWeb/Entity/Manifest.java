package com.example.testWeb.Entity;

import com.example.testWeb.config.ManifestDeserializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ManifestDeserializer.class)
public class Manifest {

    String name;

//    @JsonProperty("max_sol")
    private int maxSol;

//    @JsonProperty("max_date")
    private String earthDate;

//    private List<String> sol = new ArrayList<>();
    private List<Sol> sol = new ArrayList<>();

    public Manifest() {

    }

    public void createSols(List<String> sol, List<String> totalPhotos, List<List<String>> cameras){
        List<Sol> solList = new ArrayList<>();
        for (int i = 0; i < sol.size(); i++) {

            Sol newSol = new Sol(
                    sol.get(i),
                    totalPhotos.get(i),
                    cameras.get(i)

            );
            solList.add(newSol);
        }
        this.sol = solList;

    }


//    public List<String> getLostSols(){
//        int lastSol = Integer.parseInt(sol.get(sol.size()-1));
//        List<String> lostSol = new ArrayList<>();
//        for (int i = 0; i < lastSol; i++) {
//            if(!sol.contains(String.valueOf(i))){
//                lostSol.add(String.valueOf(i));
//            }
//        }
//        return lostSol;
//    }

    public List<String> getLostSols(){
        int lastSol = Integer.parseInt(sol.get(sol.size()-1).getSol());
        List<String> sols = new ArrayList<>();
        for(Sol sol : sol){
            sols.add(sol.getSol());
        }
        List<String> lostSol = new ArrayList<>();
        for (int i = 0; i < lastSol; i++) {
            if (!sols.contains(String.valueOf(i))){
                lostSol.add(String.valueOf(i));
            }
        }
        return lostSol;
    }


}
