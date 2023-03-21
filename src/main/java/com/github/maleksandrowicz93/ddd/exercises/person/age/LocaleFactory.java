package com.github.maleksandrowicz93.ddd.exercises.person.age;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Locale;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
enum LocaleFactory {

    POLAND(new Locale("pl", "PL"));

    Locale country;

    Locale get() {
        return country;
    }
}
