package com.github.maleksandrowicz93.ddd.exercises.full.name;

class InvalidNameException extends RuntimeException {

    InvalidNameException() {
        super("First and last name should not be blank");
    }
}
