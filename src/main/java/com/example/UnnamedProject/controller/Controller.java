package com.example.UnnamedProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/")
    public String index()
    {
        return "index1";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}
