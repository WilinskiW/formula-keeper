package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import org.springframework.expression.ParseException;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser {
    private final AsteriskAdder asteriskAdder;
    private final LatexDictionaryReader dictionaryReader;

    public LatexParser() {
        this.asteriskAdder = new AsteriskAdder();
        this.dictionaryReader = new LatexDictionaryReader();
    }

    public Formula parse(String input) {
        String formula = asteriskAdder.addAsterisks(input);

        for (String latexPattern : dictionaryReader.getLatexNames()) {
            formula = replace(latexPattern, formula);
        }

        if (formula.contains("\\")) {
            throw new ParseException(formula, 0, "Parser couldn't recognise latex function");
        }

        return new Formula(input, formula, findVariables(formula));
    }

    private String replace(String latexPattern, String formula) {
        Pattern pattern = Pattern.compile(dictionaryReader.getLatexFormat(latexPattern));
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            formula = formula.replaceAll(pattern.pattern(), dictionaryReader.getReplacementFormat(latexPattern));
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
