package com.wilinskiw.portfolio.formula_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("")
    public String viewIndex(){
        return "index";
    }

    @GetMapping("/form")
    public String viewForm() {
        return "formula-form";
    }

    @GetMapping("/user")
    public String viewUserPanel() {
        return "user-panel";
    }
}
