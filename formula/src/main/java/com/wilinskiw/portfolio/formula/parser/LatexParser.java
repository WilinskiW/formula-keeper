package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.utils.MathUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatexParser implements CalculationParser{

    @Override
    public String parse(String input) {
        String formula = input;
        for (LatexDictionary latex : LatexDictionary.values()) {
            formula = replace(latex, formula);
        }
        return formula;
    }

    private String replace(LatexDictionary latex, String formula){
        Pattern pattern = Pattern.compile(latex.getLatexFormat());
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            formula = matcher.replaceAll(latex.getValue());
            matcher = pattern.matcher(formula);
        }
        return formula;
    }

    public void findVariables(String formula){

    }

    @Override
    public double calculate(String formula) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("mathUtils", new MathUtils());

        try {
            Double result = parser.parseExpression(formula).getValue(context, Double.class);
            return result != null ? result : 0;
        } catch (ParseException e) {
            System.out.println("Unable to convert latex to calculation format.");
            return 0;
        }
    }
}
