package com.wilinskiw.portfolio.formula_data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
