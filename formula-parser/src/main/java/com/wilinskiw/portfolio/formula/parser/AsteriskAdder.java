package com.wilinskiw.portfolio.formula.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Adds asterisks between numbers, variables, and brackets in mathematical formulas to ensure correct parsing.
 */
public class AsteriskAdder {
    private String formulaText;

    /**
     * Adds asterisks to the input formula for proper parsing.
     *
     * @param input The input formula string.
     * @return The modified formula string with asterisks added.
     */
    public String addAsterisks(String input) {
        this.formulaText = input;
        this.formulaText = addAsteriskInWords();
        return addAsteriskNextToBrackets();
    }

    /**
     * Adds asterisks between consecutive characters in words.
     *
     * @return The modified formula string with added asterisks in words.
     */
    private String addAsteriskInWords() {
        Pattern pattern = Pattern.compile("(?<!\\\\)\\b\\d*[a-zA-Z]{2,}|\\d+[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(formulaText);

        StringBuilder result = new StringBuilder();
        int lastEnd = 0;

        while (matcher.find()) {
            result.append(formulaText, lastEnd, matcher.start());

            String match = matcher.group();

            String transformed = match.replaceAll("(.)", "$1*");
            transformed = transformed.substring(0, transformed.length() - 1);

            result.append(transformed);
            lastEnd = matcher.end();
        }

        result.append(formulaText.substring(lastEnd));

        return result.toString();
    }

    /**
     * Adds asterisks between variables/number and brackets
     *
     * @return The modified formula string with added asterisks.
     */
    private String addAsteriskNextToBrackets(){
        formulaText = addAsteriskToLeftBrackets();
        formulaText = addAsteriskToRightBrackets();
        return formulaText;
    }

    private String addAsteriskToLeftBrackets(){
        Pattern pattern = Pattern.compile("(?<!\\\\lef)(\\w)\\(");
        Matcher matcher = pattern.matcher(formulaText);

        while (matcher.find()) {
            formulaText = formulaText.replaceAll(pattern.pattern(), "$1*(");
        }

        return formulaText;
    }

    private String addAsteriskToRightBrackets(){
        Pattern pattern = Pattern.compile("\\)(\\w)");
        Matcher matcher = pattern.matcher(formulaText);

        while (matcher.find()) {
            formulaText = formulaText.replaceAll(pattern.pattern(), ")*$1");
        }

        return formulaText;
    }
}
