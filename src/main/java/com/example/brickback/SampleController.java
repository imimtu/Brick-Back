package com.example.brickback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @RequestMapping("/sample")
    public String sampleTest(){
        return "sampleTest";
    }

}
