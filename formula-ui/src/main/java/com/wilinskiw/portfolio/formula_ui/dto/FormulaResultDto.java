package com.wilinskiw.portfolio.formula_ui.dto;

public record FormulaResultDto (String latex, double outcome) {
    public String getLatexJax() {
        return "$$ " + latex + " $$";
    }
}
