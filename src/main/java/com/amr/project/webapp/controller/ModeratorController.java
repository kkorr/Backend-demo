package com.amr.project.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author denisqaa on 07.08.2021.
 * @project platform
 */

@RequestMapping("/moderator")
@Controller
public class ModeratorController {

    @GetMapping
    public String getModeratorPage() {
        return "moderator_page";
    }

}
