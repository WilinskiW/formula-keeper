package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.utils.MathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathUtilsTest {
    private MathUtils mathUtils;
    @BeforeEach
    public void setUp() {
        this.mathUtils = new MathUtils();
    }

    @Test
    public void divNotByZeroTest() {
        double result = mathUtils.div(0,2);
        assertEquals(0, result);
    }

    @Test
    public void divByZeroTest() {
        assertThrows(ArithmeticException.class, () -> mathUtils.div(2,0));
    }

    @Test
    public void nSqrtNotEvenBaseAndNumberBelowZeroTest() {
        double result = mathUtils.nSqrt(-27,3);
        assertEquals(-3, result);
    }

    @Test
    public void nSqrtEvenBaseAndNumberBelowZeroTest() {
        double result = mathUtils.nSqrt(-4,2);
        assertEquals(-2, result);
    }

    @Test
    public void logBaseWhenBaseIsUnderOneTest() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.logBase(0,1));
    }

    @Test
    public void logBaseWhenBaseIsEqualOneTest() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.logBase(1,1));
    }

    @Test
    public void logBaseWhenValueIsBelowZeroTest() {
        assertThrows(IllegalArgumentException.class, () -> mathUtils.logBase(2,-1));
    }

    @Test
    public void logBaseWhenBaseAndValueAreCorrect() {
        double result = mathUtils.logBase(2,4);
        assertEquals(2, result);
    }

    @Test
    public void sinTest() {
        double result = mathUtils.sin(90);
        assertEquals(1, result);
    }

    @Test
    public void cosTest() {
        double result = mathUtils.cos(90);
        assertEquals(0, result);
    }

    @Test
    public void tanTest() {
        double result = mathUtils.tan(45);
        assertEquals(1, result);
    }

    @Test
    public void tanUndefinedAngleTest() {
        assertThrows(ArithmeticException.class, () -> System.out.println(mathUtils.tan(90)));
    }

}
