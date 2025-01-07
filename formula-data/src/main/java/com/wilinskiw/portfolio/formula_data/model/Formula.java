package com.wilinskiw.portfolio.formula_data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "formulas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formula_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "latex_formula")
    private String latexFormula;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "formula_tag",
            joinColumns = @JoinColumn(name = "formula_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    private Set<Tag> tags;
}
