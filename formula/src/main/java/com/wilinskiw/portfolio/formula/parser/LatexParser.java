package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import org.springframework.expression.ParseException;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser{
    private final LatexAsteriskAdder latexAsteriskAdder;

    public LatexParser() {
        this.latexAsteriskAdder = new LatexAsteriskAdder();
    }

    public Formula parse(String input) {
        String formula = latexAsteriskAdder.addAsterisks(input);

        for (LatexDictionary latex : LatexDictionary.values()) {
            formula = replace(latex, formula);
        }

        if(formula.contains("\\")){
            throw new ParseException(formula, 0, "Parser couldn't recognise latex function");
        }

        return new Formula(input, formula, findVariables(formula));
    }


    private String replace(LatexDictionary latex, String formula) {
        Pattern pattern = Pattern.compile(latex.getLatexFormat());
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            formula = formula.replaceAll(pattern.pattern(), latex.getValue());
        }
        return formula;
    }

    private Map<String, Double> findVariables(String formula) {
        Pattern pattern = Pattern.compile("(?<![a-zA-Z0-9])([a-zA-Z])(?![a-zA-Z0-9(])");
        Matcher matcher = pattern.matcher(formula);
        Map<String, Double> variables = new TreeMap<>();

        while (matcher.find()) {
            variables.put(matcher.group(), 0.0);
        }

        return variables;
    }
}
