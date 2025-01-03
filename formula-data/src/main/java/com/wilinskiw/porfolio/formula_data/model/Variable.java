package com.wilinskiw.porfolio.formula_data.model;

import jakarta.persistence.*;

@Entity
public class Variable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formula_id", nullable = false)
    private Formula formula;


    private String name;

    @Column(nullable = false)
    private Character letter;

    @Column(name = "default_value")
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

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character symbol) {
        this.letter = symbol;
    }

    public Double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }
}
