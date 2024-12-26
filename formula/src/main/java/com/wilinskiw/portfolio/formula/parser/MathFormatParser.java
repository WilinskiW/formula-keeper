package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;

import java.util.Map;

public interface MathFormatParser {
    Formula parse(String input);
    Map<String, Double> findVariables(String formula);
}
