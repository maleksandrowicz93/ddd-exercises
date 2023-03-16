package com.github.maleksandrowicz93.ddd.exercises.pesel


import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

import static com.github.maleksandrowicz93.ddd.exercises.pesel.InvalidPeselCause.*

class PeselNumberSpec extends Specification {

    @Unroll("pesel: #pesel, day of birth: #dayOfBirth, sex: #sex")
    def "values from pesel number should be calculated correctly"() {
        when:
        def peselNumber = new PeselNumber(pesel)

        then:
        peselNumber.extractDateOfBirth() == dayOfBirth
        peselNumber.extractSex() == sex

        where:
        pesel         | dayOfBirth                 | sex
        "02070803628" | LocalDate.of(1902, 7, 8)   | Sex.WOMAN
        "68040378394" | LocalDate.of(1968, 4, 3)   | Sex.MAN
        "68112123152" | LocalDate.of(1968, 11, 21) | Sex.MAN
        "51041588262" | LocalDate.of(1951, 4, 15)  | Sex.WOMAN
        "70022232695" | LocalDate.of(1970, 2, 22)  | Sex.MAN
        "55090659456" | LocalDate.of(1955, 9, 06)  | Sex.MAN
        "97100829424" | LocalDate.of(1997, 10, 8)  | Sex.WOMAN
        "78062739338" | LocalDate.of(1978, 6, 27)  | Sex.MAN
        "82090428373" | LocalDate.of(1982, 9, 4)   | Sex.MAN
        "67072824127" | LocalDate.of(1967, 7, 28)  | Sex.WOMAN
        "93031661975" | LocalDate.of(1993, 3, 16)  | Sex.MAN
        "01251673584" | LocalDate.of(2001, 5, 16)  | Sex.WOMAN
    }

    @Unroll("pesel: #pesel, cause: #cause")
    def "pesel number should not be created from invalid source"() {
        when:
        new PeselNumber(pesel)

        then:
        def e = thrown(InvalidPeselException)
        e.invalidPeselCause() == cause

        where:
        pesel          | cause
        "02070803622"  | INCORRECT_CONTROL_DIGIT
        "0207080362"   | INVALID_LENGTH
        "020708036222" | INVALID_LENGTH
        "0207080362A"  | NOT_A_NUMBER
    }

    @Unroll("pesel: #pesel")
    def "pesel number should not be created from source with invalid date"() {
        when:
        new PeselNumber(pesel)

        then:
        def e = thrown(InvalidPeselException)
        e.invalidPeselCause() == INVALID_BIRTH_DAY

        where:
        pesel << [
                "02022903628",
                "00022903624",
                "00223003626",
                "00213203629",
                "00233203621",
                "00243103625",
                "00253203623",
                "00263103627",
                "00273203625",
                "00283203626",
                "00293103620",
                "00303203621",
                "00313103625",
                "00323203623"
        ]
    }
}
