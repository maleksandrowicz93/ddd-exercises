package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

class InvalidShoeSizeDataException extends RuntimeException {

    InvalidShoeSizeDataException(InvalidShoeSizeDataCause cause) {
        super(cause.toString());
    }
}
