package com.wilinskiw.portfolio.formula_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormulaController {
    @GetMapping("/form")
    public String viewForm() {
        return "formula-form";
    }

    @PostMapping("/form")
    public String getResult(@RequestParam("latex") String mathField, Model model) {
        return "formula-result";
    }
}
