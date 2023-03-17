package com.github.maleksandrowicz93.ddd.exercises.pesel.number;

import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.github.maleksandrowicz93.ddd.exercises.pesel.number.InvalidPeselCause.INCORRECT_CONTROL_DIGIT;
import static com.github.maleksandrowicz93.ddd.exercises.pesel.number.InvalidPeselCause.INVALID_BIRTH_DAY;
import static com.github.maleksandrowicz93.ddd.exercises.pesel.number.InvalidPeselCause.INVALID_LENGTH;
import static com.github.maleksandrowicz93.ddd.exercises.pesel.number.InvalidPeselCause.NOT_A_NUMBER;

@ToString
@FieldDefaults(makeFinal = true)
class PeselNumber {

    String value;
    LocalDate dateOfBirth;
    Sex sex;

    PeselNumber(String source) {
        if (source == null) {
            throw new InvalidPeselException(NOT_A_NUMBER);
        }
        value = source.trim();
        validateValue();
        try {
            dateOfBirth = calculateDayOfBirth();
        } catch (DateTimeException e) {
            throw new InvalidPeselException(INVALID_BIRTH_DAY);
        }
        sex = calculateSex();
    }

    private void validateValue() {
        validateLength();
        validateDigits();
        validateControlDigit();
    }

    private void validateLength() {
        if (value.length() != 11) {
            throw new InvalidPeselException(INVALID_LENGTH);
        }
    }

    private void validateDigits() {
        try {
            for (var character : value.toCharArray()) {
                Integer.parseInt(String.valueOf(character));
            }
        } catch (NumberFormatException e) {
            throw new InvalidPeselException(NOT_A_NUMBER);
        }
    }

    private void validateControlDigit() {
        int calculatedControlDigit = calculateControlDigit(value);
        int peselControlDigit = extractFromPesel(10, 11);
        if (calculatedControlDigit != peselControlDigit) {
            throw new InvalidPeselException(INCORRECT_CONTROL_DIGIT);
        }
    }

    private int calculateControlDigit(String pesel) {
        List<Integer> weightFactors = List.of(1, 3, 7, 9);
        List<Integer> peselDigits = pesel.chars()
                .mapToObj(Character::getNumericValue)
                .toList();
        int controlSum = 0;
        for (int i = 0; i < peselDigits.size() - 1; i++) {
            var digit = peselDigits.get(i);
            var modulo = i % weightFactors.size();
            var weight = weightFactors.get(modulo);
            var weightedProduct = digit * weight;
            var weightedProductLastDigit = weightedProduct % 10;
            controlSum += weightedProductLastDigit;
        }
        int controlSumLastDigit = controlSum % 10;
        return controlSumLastDigit == 0
                ? 0
                : 10 - controlSumLastDigit;
    }

    private LocalDate calculateDayOfBirth() {
        var yearWithinCentury = extractFromPesel(0, 2);
        var centuryCodeAndMonthSum = extractFromPesel(2, 4);
        var month = centuryCodeAndMonthSum % 20;
        var centuryCode = centuryCodeAndMonthSum - month;
        var centuryStartYear = centuryCode == 80
                ? 1800
                : 1900 + 5 * centuryCode;
        var year = centuryStartYear + yearWithinCentury;
        var dayOfMonth = extractFromPesel(4, 6);
        return LocalDate.of(year, month, dayOfMonth);
    }

    private int extractFromPesel(int start, int end) {
        return Integer.parseInt(value.substring(start, end));
    }

    private Sex calculateSex() {
        return extractFromPesel(9, 10) % 2 == 0
                ? Sex.WOMAN
                : Sex.MAN;
    }

    LocalDate extractDateOfBirth() {
        return LocalDate.of(dateOfBirth.getYear(),
                dateOfBirth.getMonth(),
                dateOfBirth.getDayOfMonth());
    }

    Sex extractSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeselNumber that)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
