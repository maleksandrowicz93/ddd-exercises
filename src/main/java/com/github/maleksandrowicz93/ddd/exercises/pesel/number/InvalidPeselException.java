package com.github.maleksandrowicz93.ddd.exercises.pesel.number;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true)
class InvalidPeselException extends RuntimeException {

    InvalidPeselCause invalidPeselCause;

    InvalidPeselException(InvalidPeselCause invalidPeselCause) {
        super(invalidPeselCause.toString());
        this.invalidPeselCause = invalidPeselCause;
    }
}
