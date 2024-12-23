package com.wilinskiw.portfolio.formula.parser;

public interface CalculationParser {
    String parse(String input);
    double calculate(String formula);
}
