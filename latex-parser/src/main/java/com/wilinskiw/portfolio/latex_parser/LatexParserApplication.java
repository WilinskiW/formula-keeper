package com.wilinskiw.portfolio.latex_parser;

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
        String input = "5\\cdot5";
        LatexParser latexParser = new LatexParser();
        System.out.println(latexParser.calculate(input));
    }

}
