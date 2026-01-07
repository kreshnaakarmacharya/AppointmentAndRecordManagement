package com.hospital.hosp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class indexController {
    @GetMapping("/")
    public String getindex(){

        return "Index";
    }
}
