package com.github.maleksandrowicz93.ddd.exercises.person.age;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
enum LocaleRestrictions implements AlcoholRestriction {
    US(Locale.US) {
        @Override
        public int alcoholMinimumAge() {
            return 21;
        }
    },
    JAPAN(Locale.JAPAN) {
        @Override
        public int alcoholMinimumAge() {
            return 20;
        }
    },
    POLAND(LocaleFactory.POLAND.get()) {
        @Override
        public int alcoholMinimumAge() {
            return 18;
        }
    };

    private static final Map<Locale, LocaleRestrictions> localeRestrictions = new HashMap<>();

    Locale country;

    static {
        for (LocaleRestrictions restrictions : values()) {
            localeRestrictions.put(restrictions.country, restrictions);
        }
    }

    static LocaleRestrictions of(Locale country) {
        return localeRestrictions.get(country);
    }
}
