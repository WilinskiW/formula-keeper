package com.wilinskiw.porfolio.formula_data.model;

import jakarta.persistence.*;

@Entity
public class Variable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formula_id", nullable = false)
    private Formula formula;

    private String name;

    @Column(name = "variable_symbol", nullable = false)
    private Character variableSymbol;

    private Double defaultValue;

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getVariableSymbol() {
        return variableSymbol;
    }

    public void setVariableSymbol(Character symbol) {
        this.variableSymbol = symbol;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }
}
