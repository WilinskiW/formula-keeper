package com.wilinskiw.portfolio.formula.model;

import java.util.Map;

public class Formula {
    private final String inputForm;
    private final String parsedForm;
    private final Map<String, Double> variables;

    public Formula(String inputForm, String parsedForm, Map<String, Double> variables) {
        this.inputForm = inputForm;
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

    public String getInputForm() {
        return inputForm;
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
                "latex='" + inputForm + '\'' +
                ", parsedForm='" + parsedForm + '\'' +
                ", variables=" + variables +
                '}';
    }
}
