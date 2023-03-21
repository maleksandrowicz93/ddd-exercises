package com.github.maleksandrowicz93.ddd.exercises.exam.result;

import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Objects;

@FieldDefaults(makeFinal = true)
class ExamResult {

    BigDecimal percentage;

    ExamResult(float percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Score should be value between 0 and 100.");
        }
        this.percentage = BigDecimal.valueOf(percentage);
    }

    ExamResult(float score, float max) {
        if (score < 0 || score > max) {
            throw new IllegalArgumentException("Score should be value between 0 and max.");
        }
        percentage = BigDecimal.valueOf(score * 100 / max);
    }

    boolean isBelowThreshold(float percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Threshold should be value between 0 and 100.");
        }
        return this.percentage.compareTo(BigDecimal.valueOf(percentage)) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamResult that)) return false;
        return percentage.equals(that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentage);
    }

    @Override
    public String toString() {
        return percentage.toString();
    }
}
