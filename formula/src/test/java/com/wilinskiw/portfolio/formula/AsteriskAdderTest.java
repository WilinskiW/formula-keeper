package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.parser.AsteriskAdder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsteriskAdderTest {
    private AsteriskAdder adder;
    @BeforeEach
    void setUp() {
        this.adder = new AsteriskAdder();
    }

    @Test
    public void addAsteriskToVariables(){
        String result = adder.addAsterisks("abc+ab-4*a-2");
        assertEquals("a*b*c+a*b-4*a-2", result);
    }

    @Test
    public void addAsteriskToVariablesWhileThereAreNoVariables() {
        String result = adder.addAsterisks("421+4251*5^4");
        assertEquals("421+4251*5^4", result);
    }


    @Test
    public void addAsteriskToVariablesWhileLatexFormatIsPresent(){
        String result = adder.addAsterisks("\\frac{2}{4}+5\\cdot2-4ac");
        assertEquals("\\frac{2}{4}+5\\cdot2-4*a*c", result);
    }

    @Test
    public void addAsteriskToLeftBrackets(){
        String result = adder.addAsterisks("5(a+b)-4(2(10(10-4)))");
        assertEquals("5*(a+b)-4*(2*(10*(10-4)))", result);
    }

    @Test
    public void addAsteriskToRightBrackets(){
        String result = adder.addAsterisks("(a-b)10+((5-a)6)-(10-t)c");
        assertEquals("(a-b)*10+((5-a)*6)-(10-t)*c", result);
    }

    @Test
    public void addAsteriskToBrackets(){
        String result = adder.addAsterisks("5(a+b)a-4(2(10(10-4)x)4)c");
        assertEquals("5*(a+b)*a-4*(2*(10*(10-4)*x)*4)*c", result);
    }

}
