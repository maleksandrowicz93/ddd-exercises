package com.github.maleksandrowicz93.ddd.exercises.pesel;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
enum InvalidPeselCause {
    INVALID_LENGTH("Invalid pesel length. Should have 11 digits."),
    NOT_A_NUMBER("Received pesel is not a correct number. There are not allowed characters."),
    INVALID_BIRTH_DAY("Day or month of birthday are invalid."),
    INCORRECT_CONTROL_DIGIT("Control digit is incorrect");

    String text;


    @Override
    public String toString() {
        return text;
    }
}
