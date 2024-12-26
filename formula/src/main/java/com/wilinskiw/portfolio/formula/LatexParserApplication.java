package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.FormulaCalculator;
import com.wilinskiw.portfolio.formula.parser.MathFormatParser;
import com.wilinskiw.portfolio.formula.parser.latex.LatexParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LatexParserApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LatexParserApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String latexInput = "\\frac{a}(4}-b\\cdot5++2";
        MathFormatParser latexParser = new LatexParser();
        Formula formula = latexParser.parse(latexInput);
        System.out.println("Converted Formula: " + formula);
        System.out.println(new FormulaCalculator().evaluate(formula));
    }

}
