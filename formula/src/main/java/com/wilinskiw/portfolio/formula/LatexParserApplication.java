package com.wilinskiw.portfolio.formula;

import com.wilinskiw.portfolio.formula.parser.CalculationParser;
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
    public void run(String... args)  {
        String latexInput = "50\\% * 54";
        CalculationParser latexParser = new LatexParser();
        String formula = latexParser.parse(latexInput);
        System.out.println("Converted Formula: " + formula);
        System.out.println(latexParser.calculate(formula));
    }

}
