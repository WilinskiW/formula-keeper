package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;

public interface CalculationParser {
    Formula parse(String input);
    String addAsterisks(String input);
    double calculate(Formula formula);
}
