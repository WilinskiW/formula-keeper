package com.wilinskiw.portfolio.formula_web_api.controller;

import org.springframework.stereotype.Controller;
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
    public String getFormula(@RequestParam("latex") String mathField) {
        return "redirect:/form";
    }
}
