package com.wilinskiw.portfolio.formula_web_api.service;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.FormulaCalculator;
import com.wilinskiw.portfolio.formula.parser.LatexParser;
import com.wilinskiw.portfolio.formula_web_api.dto.FormulaResultDto;
import org.springframework.stereotype.Service;

@Service
public class FormulaWebService {
    private final LatexParser parser;
    private final FormulaCalculator calculator;

    public FormulaWebService() {
        this.parser = new LatexParser();
        this.calculator = new FormulaCalculator();
    }

    public FormulaResultDto parseFormula(String input) {
        Formula formula = parser.parse(input);
        double outcome = calculator.evaluate(formula);
        return new FormulaResultDto(formula.inputForm(), outcome);
    }
}
