package com.wilinskiw.portfolio.formula.utils;

/**
 * Provides mathematical utility methods for operations such as division, roots, and trigonometric calculations.
 */
public class MathUtils {
    /**
     * Divides two numbers and checks for division by zero.
     *
     * @param a The numerator.
     * @param b The denominator.
     * @return The result of the division.
     * @throws ArithmeticException If division by zero is attempted.
     */
    public double div(double a, double b) {
        if (b != 0) {
            return a / b;
        } else
            throw new ArithmeticException("Error. Divided by 0");
    }

    /**
     * Calculates the n-th root of a number.
     *
     * @param x The number.
     * @param n The root degree.
     * @return The n-th root of the number.
     */
    public double nSqrt(double x, double n) {
        if (x < 0 && n % 2 != 0) {
            return -Math.pow(-x, 1.0 / n);
        } else if (x < 0) {
            return -Math.pow(-x, 1.0 / n);
        }
        return Math.pow(x, 1.0 / n);
    }

    /**
     * Calculates the logarithm of a value with a specified base.
     *
     * @param base  The base of the logarithm.
     * @param value The value to calculate the logarithm for.
     * @return The calculated logarithm.
     * @throws IllegalArgumentException If the base or value is invalid.
     */
    public double logBase(double base, double value) {
        if (base <= 0 || base == 1) {
            throw new IllegalArgumentException("Log base must be greater than 0 and can't equal 1");
        }

        if (value <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        return Math.log(value) / Math.log(base);
    }

    /**
     * Calculates the sine of an angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The sine of the angle.
     */
    public double sin(double angle) {
        double angRadians = Math.toRadians(angle);
        double result = Math.sin(angRadians);
        if (Math.abs(result) < 1e-10) {
            return 0.0;
        }
        return result;
    }

    /**
     * Calculates the cosine of an angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The cosine of the angle.
     */
    public double cos(double angle) {
        double angRadians = Math.toRadians(angle);
        double result = Math.cos(angRadians);
        if (Math.abs(result) < 1e-10) {
            return 0.0;
        }
        return result;
    }

    /**
     * Calculates the tan sine of an angle in degrees.
     *
     * @param angle The angle in degrees.
     * @return The tan sine of the angle.
     * @throws ArithmeticException If angle is undefined for tan
     */
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
