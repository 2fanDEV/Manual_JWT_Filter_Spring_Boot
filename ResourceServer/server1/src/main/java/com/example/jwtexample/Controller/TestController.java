package com.example.jwtexample.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/getTest")
    public String successful()
    {
        return "It worked!";
    }

}
