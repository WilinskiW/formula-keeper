package com.wilinskiw.portfolio.formula_service.service;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.FormulaCalculator;
import com.wilinskiw.portfolio.formula.parser.LatexParser;
import com.wilinskiw.portfolio.formula_service.dto.FormulaResultDto;
import org.springframework.stereotype.Service;

@Service
public class FormulaCalculationService {
    private final LatexParser parser;
    private final FormulaCalculator calculator;

    public FormulaCalculationService() {
        this.parser = new LatexParser();
        this.calculator = new FormulaCalculator();
    }

    public FormulaResultDto parseFormula(String input) {
        Formula formula = parser.parse(input);
        double outcome = calculator.evaluate(formula);
        return new FormulaResultDto(formula.inputForm(), outcome);
    }
}
