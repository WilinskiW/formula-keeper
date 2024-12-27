package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.FormulaCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParseException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FormulaCalculatorTest {
    private FormulaCalculator formulaCalculator;

    @BeforeEach
    void setUp() {
        this.formulaCalculator = new FormulaCalculator();
    }

    @Test
    public void evaluateCorrectParsedFormula() {
        Formula formula = new Formula(
                "\\frac{a}{4}-b\\cdot5+2",
                "#mathUtils.div(a,4)-b*5+2",
                Map.of("a",0.0,"b",0.0));
        double result = formulaCalculator.evaluate(formula);
        assertEquals(2.0, result);
    }

    @Test
    public void evaluateInCorrectParsedFormula() {
        Formula formula = new Formula(
                "\\frac{a}(4}-b\\cdot5++2",
                "\\frac{a}(4}-b*5++2",
                Map.of("a",0.0,"b",0.0));
        assertThrows(ParseException.class, () -> formulaCalculator.evaluate(formula));
    }

}
