package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.utils.MathUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser implements CalculationParser {

    @Override
    public Formula parse(String input) {
        String formula = addAsterisks(input);
        for (LatexDictionary latex : LatexDictionary.values()) {
            formula = replace(latex, formula);
        }
        return new Formula(input, formula, addVariables(formula));
    }

    @Override
    public String addAsterisks(String input) {
        input = addAsteriskInVariables(input);
        return addAsteriskNextToBrackets(input);

    }

    private String addAsteriskInVariables(String latexInput) {
        Pattern pattern = Pattern.compile("(?<!\\\\)\\b\\d*[a-zA-Z]{2,}|\\d+[a-zA-Z]+\\b");
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

    private String addAsteriskNextToBrackets(String input){
        input = addAsteriskToLeftBrackets(input);
        input = addAsteriskToRightBrackets(input);
        return input;
    }

    private String addAsteriskToLeftBrackets(String input){
        Pattern pattern = Pattern.compile("(?<!\\\\lef)(\\w)\\(");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            input = input.replaceAll(pattern.pattern(), "$1*(");
        }

        return input;
    }

    private String addAsteriskToRightBrackets(String input){
        Pattern pattern = Pattern.compile("\\)(\\w)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            input = input.replaceAll(pattern.pattern(), ")*$1");
        }

        return input;
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

    private Map<String, Double> addVariables(String formula) {
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
