package com.example.demo;

import com.example.demo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private CoffeeService testService;

    @GetMapping
    String test() throws Exception {
        return testService.test();
    }
}
