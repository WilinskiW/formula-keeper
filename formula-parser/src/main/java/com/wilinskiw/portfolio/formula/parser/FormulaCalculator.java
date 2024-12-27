package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.utils.MathUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class FormulaCalculator {
    private final ExpressionParser parser;
    private final EvaluationContext context;

    public FormulaCalculator() {
        this.parser = new SpelExpressionParser();
        this.context = new StandardEvaluationContext();
        context.setVariable("mathUtils", new MathUtils());
    }

    public double evaluate(Formula formula) {
        try {
            Double result = parser.parseExpression(formula.putValuesOfVariables()).getValue(context, Double.class);
            return result != null ? result : 0;
        } catch (ParseException e) {
            throw new ParseException(formula.parsedForm(), e.getPosition(), "Input expression could not be parsed," +
                    "because of unexpected character");
        }
    }
}
