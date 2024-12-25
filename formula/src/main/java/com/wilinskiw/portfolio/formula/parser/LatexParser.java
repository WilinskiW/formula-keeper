package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.utils.MathUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser implements CalculationParser {

    @Override
    public Formula parse(String input) {
        String formula = addAsterisk(input);
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

    public String addAsterisk(String latexInput) {

        String regex = "(?<!\\\\)\\b\\w{2,}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(latexInput);

        StringBuilder result = new StringBuilder();
        int lastEnd = 0;

        while (matcher.find()) {
            result.append(latexInput, lastEnd, matcher.start());

            String match = matcher.group();

            String transformed = match.replaceAll("(.)", "$1*");
            transformed = transformed.substring(0, transformed.length() - 1);

            result.append(transformed);
            lastEnd = matcher.end();
        }

        result.append(latexInput.substring(lastEnd));

        return result.toString();
    }

    public Map<String, Double> findVariables(String formula) {
        Pattern pattern = Pattern.compile("(?<![a-zA-Z0-9])([a-zA-Z])(?![a-zA-Z0-9(])");
        Matcher matcher = pattern.matcher(formula);
        Map<String, Double> variables = new TreeMap<>();

        while (matcher.find()) {
            variables.put(matcher.group(), 0.0);
        }

        return variables;
    }

    @Override
    public double calculate(Formula formula) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("mathUtils", new MathUtils());

        try {
            Double result = parser.parseExpression(formula.putValuesOfVariables()).getValue(context, Double.class);
            return result != null ? result : 0;
        } catch (ParseException e) {
            System.out.println("Unable to convert latex to calculation format.");
            return 0;
        }
    }
}
