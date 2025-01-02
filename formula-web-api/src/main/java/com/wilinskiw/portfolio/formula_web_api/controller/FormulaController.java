package com.wilinskiw.portfolio.formula_web_api.controller;

import com.wilinskiw.portfolio.formula_web_api.service.FormulaWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormulaController {
    private final FormulaWebService webService;

    @Autowired
    public FormulaController(FormulaWebService webService) {
        this.webService = webService;
    }

    @GetMapping("/form")
    public String viewForm() {
        return "formula-form";
    }

    @PostMapping("/form")
    public String getResult(@RequestParam("latex") String mathField, Model model) {
        model.addAttribute("result", webService.parseFormula(mathField));
        return "formula-result";
    }
}
