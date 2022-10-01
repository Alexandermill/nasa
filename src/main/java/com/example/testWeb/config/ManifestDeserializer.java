package com.example.testWeb.config;

import com.example.testWeb.Entity.Manifest;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManifestDeserializer extends StdDeserializer<Manifest> {

    public ManifestDeserializer(){
        this(null);
    }

    public ManifestDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public Manifest deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode manifestNode = jp.getCodec().readTree(jp);
        Manifest manifest = new Manifest();
        manifest.setName(manifestNode.get("photo_manifest").get("name").textValue());
        manifest.setMaxSol(manifestNode.get("photo_manifest").get("max_sol").intValue());
        manifest.setEarthDate(manifestNode.get("photo_manifest").get("max_date").textValue());
//        manifest.setSol(manifestNode.get("photo_manifest").get("photos").findValuesAsText("sol"));

        manifest.createSols(manifestNode.get("photo_manifest").get("photos").findValuesAsText("sol"),
                manifestNode.get("photo_manifest").get("photos").findValuesAsText("total_photos"),
                convertJsonNodeToList(manifestNode.get("photo_manifest").get("photos").findValues("cameras"))
                );

        return manifest;
    }

    private List<List<String>> convertJsonNodeToList(List<JsonNode> jsonNodes){
        List<List<String>> listStr = new ArrayList<>();
        for (int i = 0; i < jsonNodes.size(); i++) {
            JsonNode jsonNodes2 = jsonNodes.get(i);
            List<String> list = new ArrayList<>();
            for (int j = 0; j < jsonNodes2.size(); j++) {

                list.add(jsonNodes2.get(j).asText());
            }
            listStr.add(list);
        }
        return listStr;
    }
}
