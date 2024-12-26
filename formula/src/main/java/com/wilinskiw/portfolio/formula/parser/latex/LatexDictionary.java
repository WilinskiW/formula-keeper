package com.wilinskiw.portfolio.formula.parser.latex;

public enum LatexDictionary {
    CDOT("\\\\cdot", "*"),
    DIV("\\\\div", "/"),
    FRAC("\\\\frac\\{([^}]+)}\\{([^}]+)}", "#mathUtils.div($1,$2)"),
    SQRT("\\\\sqrt\\{([^}]+)}", "T(java.lang.Math).sqrt($1)"),
    N_SQRT("\\\\sqrt\\[([^\\]+])\\]\\{([^}]+)\\}", "#mathUtils.nSqrt($2,$1)"),
    POW("(\\d+|[a-zA-Z])\\^\\{([^}]+)\\}", "T(java.lang.Math).pow($1,$2)"),
    LOG10("\\\\log(\\d+)", "T(java.lang.Math).log10($1)"),
    DIGIT_BASE_LOG("\\\\log\\_(\\d)(\\d+)", "#mathUtils.logBase($1,$2)"),
    NUMBER_BASE_LOG("\\\\log\\_\\{([^}]+)}(\\d+)", "#mathUtils.logBase($1,$2)"),
    SIN("\\\\sin(\\d+)", "#mathUtils.sin($1)"),
    COS("\\\\cos(\\d+)", "#mathUtils.cos($1)"),
    TAN("\\\\tan(\\d+)", "#mathUtils.tan($1)"),
    PI("\\\\pi", "T(java.lang.Math).PI"),
    ROUND_BRACKETS("\\\\left\\(([^)]+)\\\\right\\)", "($1)"),
    PERCENT("(\\d+)\\\\%", "$1*0.01");


    private final String latexFormat;
    private final String value;

    LatexDictionary(String latexFormat, String value) {
        this.latexFormat = latexFormat;
        this.value = value;
    }

    public String getLatexFormat() {
        return latexFormat;
    }

    public String getValue() {
        return value;
    }
}
