package org.jiahan.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){

        return "Hello World!";

    }

    @PostMapping("/hello/post")
    public String hellopost(String name){

        return "Hello World! Post," + name ;

    }
}
