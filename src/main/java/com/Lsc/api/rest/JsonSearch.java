package com.Lsc.api.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class JsonSearch {

    ObjectMapper mapper = new ObjectMapper();


    public  String findPath(String targetFile) throws IOException {
       JsonNode node = mapper.readTree(new File("src/main/java/com/Lsc/api/rest/tree_structure.json"));
       ArrayList<String> finalPaths = new ArrayList<>();
       traverseTree(node.get(0), new ArrayList<String>(), targetFile, finalPaths);
       if(finalPaths.isEmpty()){
           finalPaths.add("No file by that name");
       }
       return mapper.writeValueAsString(finalPaths);
    }

    public void traverseTree(JsonNode node, ArrayList<String> path, String targetFile,ArrayList<String> finalPaths){

        if(node.has("name") && node.get("name").textValue().equals(targetFile)){
            path.add(node.get("name").textValue());
            finalPaths.add(String.join("/", path));
            path.remove(path.size()-1);
        }
        try {
            path.add(node.get("name").textValue());
            if (node.has("contents")) {
                for (JsonNode subNode : node.get("contents")
                ) {
                    traverseTree(subNode, path, targetFile, finalPaths);
                }
            }
            path.remove(path.size()-1);
        }
        catch(Exception e) {
            System.out.println("Error opening dir");
        }
    }


}
