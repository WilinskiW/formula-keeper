package com.wilinskiw.portfolio.formula_service.controller;

import com.wilinskiw.portfolio.formula_service.dto.FormulaResultDto;
import com.wilinskiw.portfolio.formula_service.service.FormulaCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/formula")
public class FormulaController {
    private final FormulaCalculationService calculationService;

    @Autowired
    public FormulaController(FormulaCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<FormulaResultDto> getResult(@RequestBody String formula) {
        FormulaResultDto resultDto = calculationService.parseFormula(formula);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
