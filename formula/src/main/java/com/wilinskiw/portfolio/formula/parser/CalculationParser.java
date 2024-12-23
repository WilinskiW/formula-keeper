package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;

public interface CalculationParser {
    Formula parse(String input);
    double calculate(String formula);
}
