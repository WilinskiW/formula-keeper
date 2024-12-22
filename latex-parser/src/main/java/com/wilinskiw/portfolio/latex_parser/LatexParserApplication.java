package com.wilinskiw.portfolio.latex_parser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class LatexParserApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LatexParserApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        String input = "\\pi";

        Map<String, String> latexMap = getStringStringMap();

        for (String key : latexMap.keySet()) {
            Pattern pattern = Pattern.compile(key);
            Matcher matcher = pattern.matcher(input);

            if(matcher.find()){
                String value = latexMap.get(key);
                input = matcher.replaceAll(value);
            }
        }

        System.out.println("Converted Expression: " + input);

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("mathUtils", new MathUtils());

        Double result = parser.parseExpression(input).getValue(context, Double.class);

        System.out.println("Result: " + result);
    }

    private static Map<String, String> getStringStringMap() {
        Map<String, String> latexMap = new TreeMap<>();
        latexMap.put("\\\\cdot", "*"); // Mnożenie
        latexMap.put("\\\\div", "/");
        latexMap.put("\\\\frac\\{([^}]+)}\\{([^}]+)}", "($1)/($2)");// Ułamki

        latexMap.put("\\\\sqrt\\{([^}]+)}", "T(java.lang.Math).sqrt($1)"); //pierwiastkowanie 2
        latexMap.put("\\\\sqrt\\[([^\\]+])\\]\\{([^}]+)\\}", "#mathUtils.nSqrt($2,$1)"); //pierwiastkowanie n

        latexMap.put("(\\d+)\\^\\{([^}]+)\\}", "T(java.lang.Math).pow($1,$2)"); //potęgowanie

        latexMap.put("\\\\log(\\d+)", "T(java.lang.Math).log10($1)"); //logarytm o podstawie 10
        latexMap.put("\\\\log\\_(\\d)(\\d+)", "#mathUtils.logBase($1,$2)"); //logarytm o podstawie n | dla n < 10
        latexMap.put("\\\\log\\_\\{([^}]+)}(\\d+)", "#mathUtils.logBase($1,$2)"); //logarytm o podstawie n | dla n >= 10

        latexMap.put("\\\\sin(\\d+)", "T(java.lang.Math).sin(T(java.lang.Math).toRadians($1))"); //sin
        latexMap.put("\\\\cos(\\d+)", "T(java.lang.Math).cos(T(java.lang.Math).toRadians($1))"); //cos
        latexMap.put("\\\\tan(\\d+)", "T(java.lang.Math).tan(T(java.lang.Math).toRadians($1))"); //tan
        latexMap.put("\\\\pi", "T(java.lang.Math).PI"); //PI

        latexMap.put("\\\\left\\(([^)]+)\\\\right\\)", "($1)"); // nawiasy ()


        return latexMap;
    }
}
