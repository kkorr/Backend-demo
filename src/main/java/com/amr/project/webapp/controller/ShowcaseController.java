package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showcase")
public class ShowcaseController {
    public ShowcaseController() {}

    @GetMapping
    public String getShowcaseView() {
        return "showcase";
    }
    @GetMapping("/full")
    public String getFullShowcaseView() {
        return "fullShowcase";
    }
}
