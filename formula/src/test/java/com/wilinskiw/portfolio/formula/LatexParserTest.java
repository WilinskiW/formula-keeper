package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.latex.LatexParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.expression.ParseException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LatexParserTest {
    private LatexParser latexParser;

    @BeforeEach
    void setUp() {
        this.latexParser = new LatexParser();
    }

    @Test
    public void parseToObjectTest() {
        Formula testFormula = latexParser.parse("b^{4a}-4ac+\\log_24\\cdot24");

        assertEquals("b^{4a}-4ac+\\log_24\\cdot24", testFormula.inputForm());
        assertEquals("T(java.lang.Math).pow(b,4*a)-4*a*c+#mathUtils.logBase(2,4)*24", testFormula.parsedForm());
        assertEquals(Map.of("a", 0.0, "b", 0.0, "c", 0.0), testFormula.variables());
    }

    @Test
    public void parseLatexWhileLatexFunctionIsNotInDictionaryTest() {
        assertThrows(ParseException.class, () -> latexParser.parse("4\\bat4"));
    }

    @Test
    public void parseLatexCdotTest() {
        Formula testFormula = latexParser.parse("4\\cdot4+a\\cdot2");
        assertEquals("4*4+a*2", testFormula.parsedForm());
    }

    @Test
    public void parseLatexDivTest() {
        Formula testFormula = latexParser.parse("4\\div4+a\\div2");
        assertEquals("4/4+a/2", testFormula.parsedForm());
    }

    @Test
    public void parseLatexFracTest() {
        Formula testFormula = latexParser.parse("\\frac{42+6}{a}+\\frac{2}{4}");
        assertEquals("#mathUtils.div(42+6,a)+#mathUtils.div(2,4)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexSqrtTest() {
        Formula testFormula = latexParser.parse("\\sqrt{4}");
        assertEquals("T(java.lang.Math).sqrt(4)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexNsqrtTest() {
        Formula testFormula = latexParser.parse("\\sqrt[3]{27}");
        assertEquals("#mathUtils.nSqrt(27,3)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexPowTest() {
        Formula testFormula = latexParser.parse("4^{24}");
        assertEquals("T(java.lang.Math).pow(4,24)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexLog10Test() {
        Formula testFormula = latexParser.parse("\\log100");
        assertEquals("T(java.lang.Math).log10(100)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexDigitBaseLogTest(){
        Formula testFormula = latexParser.parse("\\log_24");
        assertEquals("#mathUtils.logBase(2,4)", testFormula.parsedForm());
    }


    @Test
    public void parseLatexNumberBaseLogTest(){
        Formula testFormula = latexParser.parse("\\log_{16}256");
        assertEquals("#mathUtils.logBase(16,256)", testFormula.parsedForm());
    }


    @Test
    public void parseLatexSinTest(){
        Formula testFormula = latexParser.parse("\\sin90");
        assertEquals("#mathUtils.sin(90)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexCosTest(){
        Formula testFormula = latexParser.parse("\\cos90");
        assertEquals("#mathUtils.cos(90)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexTanTest(){
        Formula testFormula = latexParser.parse("\\tan90");
        assertEquals("#mathUtils.tan(90)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexPITest(){
        Formula testFormula = latexParser.parse("\\pi");
        assertEquals("T(java.lang.Math).PI", testFormula.parsedForm());
    }

    @Test
    public void parseLatexRoundBracketsTest(){
        Formula testFormula = latexParser.parse("\\left(2+2\\right)");
        assertEquals("(2+2)", testFormula.parsedForm());
    }

    @Test
    public void parseLatexPercentTest(){
        Formula testFormula = latexParser.parse("100\\%*54");
        assertEquals("100*0.01*54", testFormula.parsedForm());
    }
}
