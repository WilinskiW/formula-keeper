package com.wilinskiw.portfolio.formula_web_api.dto;

public record FormulaResultDto (String latex, double outcome) {
    public String getLatexJax() {
        return "$$ " + latex + " $$";
    }
}
