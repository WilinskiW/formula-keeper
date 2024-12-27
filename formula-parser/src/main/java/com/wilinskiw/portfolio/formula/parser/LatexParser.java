package com.wilinskiw.portfolio.formula.parser;

import com.wilinskiw.portfolio.formula.model.Formula;
import org.springframework.expression.ParseException;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses LaTeX input and converts it into an evaluable formula.
 * Ensures proper formatting by adding asterisks where necessary and replacing recognized LaTeX patterns.
 */
public class LatexParser {
    private final AsteriskAdder asteriskAdder;
    private final LatexDictionaryReader dictionaryReader;

    public LatexParser() {
        this.asteriskAdder = new AsteriskAdder();
        this.dictionaryReader = new LatexDictionaryReader();
    }

    /**
     * Parses a LaTeX input string into a Formula object.
     *
     * @param input The LaTeX input string.
     * @return A Formula object containing the original input, parsed formula, and identified variables.
     * @throws org.springframework.expression.ParseException If the input contains unrecognized LaTeX patterns.
     */
    public Formula parse(String input) {
        String formula = asteriskAdder.addAsterisks(input);

        for (String latexPattern : dictionaryReader.getLatexNames()) {
            formula = replace(latexPattern, formula);
        }

        if (formula.contains("\\")) {
            throw new ParseException(formula, 0, "Parser couldn't recognise latex function");
        }

        return new Formula(input, formula, findVariables(formula));
    }

    /**
     * Replaces recognized LaTeX patterns in the formula with their corresponding values.
     *
     * @param latexPattern The LaTeX pattern name.
     * @param formula      The formula string to process.
     * @return The formula string with replacements applied.
     */
    private String replace(String latexPattern, String formula) {
        Pattern pattern = Pattern.compile(dictionaryReader.getLatexFormat(latexPattern));
        Matcher matcher = pattern.matcher(formula);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(result, dictionaryReader.getReplacementFormat(latexPattern));
        }
        matcher.appendTail(result);

        return result.toString();
    }


    /**
     * Identifies variables in the formula string.
     *
     * @param formula The formula string to analyze.
     * @return A map of variable names to default values (0.0).
     */
    private Map<String, Double> findVariables(String formula) {
        Pattern pattern = Pattern.compile("(?<![a-zA-Z0-9])([a-zA-Z])(?![a-zA-Z0-9(])");
        Matcher matcher = pattern.matcher(formula);
        Map<String, Double> variables = new TreeMap<>();

        while (matcher.find()) {
            variables.put(matcher.group(), 0.0);
        }

        return variables;
    }
}
