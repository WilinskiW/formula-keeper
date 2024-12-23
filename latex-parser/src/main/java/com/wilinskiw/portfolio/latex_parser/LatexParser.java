package com.wilinskiw.portfolio.latex_parser;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser {
    public double calculate(String latexInput) {
        String translatedInput = translateLatexToCalculationFormat(latexInput);
        System.out.println("Converted Expression: " + translatedInput);
        return parse(translatedInput);
    }

    private String translateLatexToCalculationFormat(String latexInput) {
        for (Latex latex : Latex.values()) {
            Pattern pattern = Pattern.compile(latex.getLatexFormat());
            Matcher matcher = pattern.matcher(latexInput);

            if (matcher.find()) {
                latexInput = latexInput.replaceAll(pattern.pattern(), latex.getValue());
            }
        }
        return latexInput;
    }

    private double parse(String latexInput){
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("mathUtils", new MathUtils());

        try {
            Double result = parser.parseExpression(latexInput).getValue(context, Double.class);
            return result != null ? result : 0;
        } catch (ParseException e) {
            System.out.println("Unable to convert latex to calculation format.");
            return 0;
        }
    }
}
