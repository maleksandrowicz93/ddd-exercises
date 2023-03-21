package com.github.maleksandrowicz93.ddd.exercises.person.age;

import lombok.experimental.FieldDefaults;

import java.time.Year;
import java.util.Locale;

@FieldDefaults(makeFinal = true)
class PersonAge {

    private static final int MAX_AGE = 125;
    private static final int MID_AGE_THRESHOLD = 12;
    private static final int ADULT_THRESHOLD = 18;
    private static final int OLD_THRESHOLD = 60;

    int age;

    PersonAge(int age) {
        if (age < 0 || age > MAX_AGE) {
            throw new IllegalArgumentException("Age should be between 0 and 125 years");
        }
        this.age = age;
    }

    PersonAge(Year year) {
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        }
        Year now = Year.now();
        int difference = now.compareTo(year);
        if (difference < 0 || difference > MAX_AGE) {
            throw new IllegalArgumentException("Year of birth should be between: " +
                    now + " and " + Year.of(now.getValue() - MAX_AGE));
        }
        age = difference;
    }

    boolean isYoung() {
        return age < MID_AGE_THRESHOLD;
    }

    boolean isMidAged() {
        return age >= MID_AGE_THRESHOLD && age < ADULT_THRESHOLD;
    }

    boolean isAdult() {
        return age >= ADULT_THRESHOLD && age <= OLD_THRESHOLD;
    }

    boolean isOld() {
        return age > OLD_THRESHOLD;
    }

    boolean canDrinkAlcohol(Locale country) {
        int alcoholMinimumAge = LocaleRestrictions.of(country).alcoholMinimumAge();
        return age >= alcoholMinimumAge;
    }
}
