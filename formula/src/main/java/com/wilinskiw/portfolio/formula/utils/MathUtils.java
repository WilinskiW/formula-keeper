package com.wilinskiw.portfolio.formula.utils;

public class MathUtils {
    public double div(double a, double b){
        try {
            return a / b;
        }
        catch (ArithmeticException e){
            throw new ArithmeticException("Error. Divided by 0");
        }
    }

    public double nSqrt(double x, double n) {
        if (x < 0 && n % 2 != 0) {
            return -Math.pow(-x, 1.0 / n);
        }
        return Math.pow(x, 1.0 / n);
    }

    public double logBase(int base, int value) {
        if (base <= 0 || base == 1) {
            throw new IllegalArgumentException("Log base must be greater than 0 and can't equal 1");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        return Math.log(value) / Math.log(base);
    }

    public double sin(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    public double cos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }

    public double tan(double angle) {
        return Math.tan(Math.toRadians(angle));
    }
}
