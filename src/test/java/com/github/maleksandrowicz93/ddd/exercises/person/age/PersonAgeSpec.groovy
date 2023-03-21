package com.github.maleksandrowicz93.ddd.exercises.person.age

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Year

class PersonAgeSpec extends Specification {

    @Shared now = Year.now().value

    @Unroll("#age")
    def "should not create PersonAge from invalid age"() {
        when:
        new PersonAge((int) age)

        then:
        thrown(IllegalArgumentException)

        where:
        age << ([-10, -1, 126, 200] as Number[])
    }

    @Unroll("#year")
    def "should not create PersonAge from invalid year of birth"() {
        when:
        new PersonAge(year)

        then:
        thrown(IllegalArgumentException)

        where:
        year << [
                Year.of(now - 126),
                Year.of(now - 200),
                Year.of(now + 1),
                Year.of(now + 10)
        ]
    }

    @Unroll("age: #age, young: #isYoung, midAged: #isMidAged, adult: #isAdult, old: #isOld")
    def "PersonAge may check what kind of old person is"() {
        given:
        def personAge = new PersonAge(age)

        expect:
        personAge.isYoung() == isYoung
        personAge.isMidAged() == isMidAged
        personAge.isAdult() == isAdult
        personAge.isOld() == isOld

        where:
        age | isYoung | isMidAged | isAdult | isOld
        0   | true    | false     | false   | false
        11  | true    | false     | false   | false
        12  | false   | true      | false   | false
        13  | false   | true      | false   | false
        17  | false   | true      | false   | false
        18  | false   | false     | true    | false
        19  | false   | false     | true    | false
        59  | false   | false     | true    | false
        60  | false   | false     | true    | false
        61  | false   | false     | false   | true
        62  | false   | false     | false   | true
        125 | false   | false     | false   | true
    }

    @Unroll("age: #age, country: #country, canDrink: #canDrink")
    def "PersonAge may check person age is enough to drink alcohol"() {
        given:
        def personAge = new PersonAge(age)

        expect:
        personAge.canDrinkAlcohol(country) == canDrink

        where:
        age | country                | canDrink
        20  | Locale.US              | false
        21  | Locale.US              | true
        19  | Locale.JAPAN           | false
        20  | Locale.JAPAN           | true
        17  | LocaleFactory.POLAND.get() | false
        18  | LocaleFactory.POLAND.get() | true
    }
}
