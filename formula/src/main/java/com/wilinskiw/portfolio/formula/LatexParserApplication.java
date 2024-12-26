package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.model.Formula;
import com.wilinskiw.portfolio.formula.parser.FormulaCalculator;
import com.wilinskiw.portfolio.formula.parser.LatexParser;
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
        String latexInput = "\\frac{a}{4}-b\\cdot5+2+\\sqrt[4.4]{4.4}";
        LatexParser latexParser = new LatexParser();
        Formula formula = latexParser.parse(latexInput);
        System.out.println("Converted Formula: " + formula);
        System.out.println(new FormulaCalculator().evaluate(formula));
    }

}
