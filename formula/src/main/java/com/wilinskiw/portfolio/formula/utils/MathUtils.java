package com.wilinskiw.portfolio.formula.utils;

public class MathUtils {
    public double div(double a, double b) {
        if (b != 0) {
            return a / b;
        } else
            throw new ArithmeticException("Error. Divided by 0");
    }

    public double nSqrt(double x, int n) {
        if (x < 0 && n % 2 != 0) {
            return -Math.pow(-x, 1.0 / n);
        } else if (x < 0) {
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
        double angRadians = Math.toRadians(angle);
        double result = Math.sin(angRadians);
        if (Math.abs(result) < 1e-10) {
            return 0.0;
        }
        return result;
    }

    public double cos(double angle) {
        double angRadians = Math.toRadians(angle);
        double result = Math.cos(angRadians);
        if (Math.abs(result) < 1e-10) {
            return 0.0;
        }
        return result;
    }

    public double tan(double angle) {
        if (Math.abs(angle % 180) == 90) {
            throw new ArithmeticException("Tangent is undefined for angle " + angle);
        }

        double angRadians = Math.toRadians(angle);
        double result = Math.tan(angRadians);

        if (Math.abs(result - 1.0) < 1e-10) {
            return 1.0;
        }

        if (Math.abs(result) > 1e10) {
            throw new ArithmeticException("Tangent is undefined for angle " + angle);
        }

        return result;
    }
}
