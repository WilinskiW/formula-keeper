package com.wilinskiw.portfolio.formula.parser.latex;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.AsteriskAdder;
import com.wilinskiw.portfolio.formula.parser.MathFormatParser;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser implements MathFormatParser {
    private final AsteriskAdder latexAsteriskAdder;

    public LatexParser() {
        this.latexAsteriskAdder = new LatexAsteriskAdder();
    }

    @Override
    public Formula parse(String input) {
        String formula = latexAsteriskAdder.addAsterisks(input);

        for (LatexDictionary latex : LatexDictionary.values()) {
            formula = replace(latex, formula);
        }
        return new Formula(input, formula, findVariables(formula));
    }


    private String replace(LatexDictionary latex, String formula) {
        Pattern pattern = Pattern.compile(latex.getLatexFormat());
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            formula = matcher.replaceAll(latex.getValue());
            matcher = pattern.matcher(formula);
        }
        return formula;
    }

    @Override
    public Map<String, Double> findVariables(String formula) {
        Pattern pattern = Pattern.compile("(?<![a-zA-Z0-9])([a-zA-Z])(?![a-zA-Z0-9(])");
        Matcher matcher = pattern.matcher(formula);
        Map<String, Double> variables = new TreeMap<>();

        while (matcher.find()) {
            variables.put(matcher.group(), 0.0);
        }

        return variables;
    }
}
