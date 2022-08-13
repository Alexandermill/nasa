package com.example.testWeb.config;

import com.example.testWeb.Manifest;
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
        manifest.setSol(manifestNode.get("photo_manifest").get("photos").findValuesAsText("sol"));
        System.out.println(manifest.getSol().size());


        return manifest;
    }
}
