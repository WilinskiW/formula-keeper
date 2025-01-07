package com.wilinskiw.portfolio.formula_ui.controller;

import com.wilinskiw.portfolio.formula_ui.dto.FormulaResultDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class FormulaController {
    private final RestTemplate restTemplate;

    public FormulaController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/calculate")
    public String calculateFormula(@RequestParam("latex") String formula, Model model) {
        FormulaResultDto resultDto = restTemplate.postForObject
                ("http://localhost:8081/api/formula/calculate", formula, FormulaResultDto.class);
        model.addAttribute("result", resultDto);
        return "formula-result";
    }
}
