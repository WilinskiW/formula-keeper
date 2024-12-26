package com.wilinskiw.portfolio.formula.model;

import java.util.Map;

public class Formula {
    private final String latex;
    private final String parsedForm;
    private final Map<String, Double> variables;

    public Formula(String latex, String parsedForm, Map<String, Double> variables) {
        this.latex = latex;
        this.parsedForm = parsedForm;
        this.variables = variables;
    }

    public String putValuesOfVariables() {
        if (variables.isEmpty()) {
            return parsedForm;
        }

        String calculationForm = parsedForm;

        for (String variable : variables.keySet()) {
            calculationForm = calculationForm.replaceAll("(?<![a-zA-Z0-9])" + variable + "(?![a-zA-Z0-9(])",
                    variables.get(variable).toString());
        }

        return calculationForm;
    }

    public String getLatex() {
        return latex;
    }

    public String getParsedForm() {
        return parsedForm;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "latex='" + latex + '\'' +
                ", parsedForm='" + parsedForm + '\'' +
                ", variables=" + variables +
                '}';
    }
}
