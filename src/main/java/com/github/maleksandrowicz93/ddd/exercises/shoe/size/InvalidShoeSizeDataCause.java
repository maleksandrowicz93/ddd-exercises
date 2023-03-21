package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
enum InvalidShoeSizeDataCause {
    NULL_VALUES("Values cannot be null."),
    TOO_SMALL_SIZE("Size should not be less than one.");

    String text;

    @Override
    public String toString() {
        return text;
    }
}
