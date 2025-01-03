package com.wilinskiw.porfolio.formula_data.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "formulas")
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
            joinColumns =@JoinColumn(name = "formula_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLatexFormula() {
        return latexFormula;
    }

    public void setLatexFormula(String latexFormula) {
        this.latexFormula = latexFormula;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
