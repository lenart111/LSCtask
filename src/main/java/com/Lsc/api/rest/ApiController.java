package com.Lsc.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ApiController {
    @Autowired
    private JsonSearch search;


    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("find/{filename}")
    public String returnPaths(@PathVariable String filename) throws IOException {
        return  search.findPath(filename);
    }

}
