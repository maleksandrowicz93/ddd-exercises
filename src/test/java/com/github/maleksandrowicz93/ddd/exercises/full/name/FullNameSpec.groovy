package com.github.maleksandrowicz93.ddd.exercises.full.name

import spock.lang.Specification
import spock.lang.Unroll

class FullNameSpec extends Specification {

    @Unroll("first: #firstName, middle: #middleName, last: #lastName, maiden: #maidenName")
    def "full name should be successful created"() {
        when:
        def fullName = FullName.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .maidenName(maidenName)
                .build()

        then:
        fullName.firstName() == firstName
        fullName.middleName() == middleName
        fullName.lastName() == lastName
        fullName.maidenName() == maidenName

        where:
        firstName  | middleName | lastName    | maidenName
        "John"     | "Paul"     | "Second"    | "Yellow"
        "Emmanuel" | null       | "Olisadebe" | null
        "Adam"     | ""         | "Małysz"    | "Orzeł"
    }

    @Unroll("first: #firstName, last: #lastName")
    def "full name should not be created when invalid data passed"() {
        when:
        FullName.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build()

        then:
        thrown(InvalidNameException)

        where:
        firstName | lastName
        null      | "Paul"
        " "       | "Paul"
        "John"    | null
        "John"    | " "
        null      | null
        " "       | " "
    }
}
