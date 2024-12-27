package com.wilinskiw.portfolio.formula.model;

import java.util.Map;

public record Formula(String inputForm, String parsedForm, Map<String, Double> variables) {
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
}
