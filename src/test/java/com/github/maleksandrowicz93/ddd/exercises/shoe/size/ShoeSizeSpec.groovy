package com.github.maleksandrowicz93.ddd.exercises.shoe.size

import spock.lang.Specification
import spock.lang.Unroll

class ShoeSizeSpec extends Specification {

    @Unroll("value: #value, type: #type, sex: #sex")
    def "ShoeSize calculates size conversions correctly"() {
        when:
        def shoeSize = ShoeSize.builder()
                .value(BigDecimal.valueOf(value))
                .type(type)
                .sex(sex)
                .build()

        then:
        shoeSize.inCm() == cm
        shoeSize.inInches() == inches
        shoeSize.toEu() == euSize
        shoeSize.toUk() == ukSize
        shoeSize.toUs() == usSize

        where:
        value  | type                | sex        | cm     | inches | euSize | ukSize | usSize

        25     | ShoeSizeType.CM     | Sex.MALE   | 25     | 9.83d  | 39.5d  | 6.5d   | 7.5d
        25     | ShoeSizeType.CM     | Sex.FEMALE | 25     | 9.83d  | 39.5d  | 6.5d   | 8.5d
        9.83d  | ShoeSizeType.INCHES | Sex.MALE   | 25     | 9.83d  | 39.5d  | 6.5d   | 7.5d
        9.83d  | ShoeSizeType.INCHES | Sex.FEMALE | 25     | 9.83d  | 39.5d  | 6.5d   | 8.5d
        39.5d  | ShoeSizeType.EU     | Sex.MALE   | 25     | 9.83d  | 39.5d  | 6.5d   | 7.5d
        39.5d  | ShoeSizeType.EU     | Sex.FEMALE | 25     | 9.83d  | 39.5d  | 6.5d   | 8.5d
        6.5d   | ShoeSizeType.UK     | Sex.MALE   | 25     | 9.83d  | 39.5d  | 6.5d   | 7.5d
        6.5d   | ShoeSizeType.UK     | Sex.FEMALE | 25     | 9.83d  | 39.5d  | 6.5d   | 8.5d
        7.5d   | ShoeSizeType.US     | Sex.MALE   | 25     | 9.83d  | 39.5d  | 6.5d   | 7.5d
        8.5d   | ShoeSizeType.US     | Sex.FEMALE | 25     | 9.83d  | 39.5d  | 6.5d   | 8.5d

        25.33d | ShoeSizeType.CM     | Sex.MALE   | 25.33d | 10     | 40     | 7      | 8
        25.33d | ShoeSizeType.CM     | Sex.FEMALE | 25.33d | 10     | 40     | 7      | 9
        10     | ShoeSizeType.INCHES | Sex.MALE   | 25.33d | 10     | 40     | 7      | 8
        10     | ShoeSizeType.INCHES | Sex.FEMALE | 25.33d | 10     | 40     | 7      | 9
        40     | ShoeSizeType.EU     | Sex.MALE   | 25.33d | 10     | 40     | 7      | 8
        40     | ShoeSizeType.EU     | Sex.FEMALE | 25.33d | 10     | 40     | 7      | 9
        7      | ShoeSizeType.UK     | Sex.MALE   | 25.33d | 10     | 40     | 7      | 8
        7      | ShoeSizeType.UK     | Sex.FEMALE | 25.33d | 10     | 40     | 7      | 9
        8      | ShoeSizeType.US     | Sex.MALE   | 25.33d | 10     | 40     | 7      | 8
        9      | ShoeSizeType.US     | Sex.FEMALE | 25.33d | 10     | 40     | 7      | 9

        25.67d | ShoeSizeType.CM     | Sex.MALE   | 25.67d | 10.17d | 40.5d  | 7.5d   | 8.5d
        25.67d | ShoeSizeType.CM     | Sex.FEMALE | 25.67d | 10.17d | 40.5d  | 7.5d   | 9.5d
        10.17d | ShoeSizeType.INCHES | Sex.MALE   | 25.67d | 10.17d | 40.5d  | 7.5d   | 8.5d
        10.17d | ShoeSizeType.INCHES | Sex.FEMALE | 25.67d | 10.17d | 40.5d  | 7.5d   | 9.5d
        40.5d  | ShoeSizeType.EU     | Sex.MALE   | 25.67d | 10.17d | 40.5d  | 7.5d   | 8.5d
        40.5d  | ShoeSizeType.EU     | Sex.FEMALE | 25.67d | 10.17d | 40.5d  | 7.5d   | 9.5d
        7.5d   | ShoeSizeType.UK     | Sex.MALE   | 25.67d | 10.17d | 40.5d  | 7.5d   | 8.5d
        7.5d   | ShoeSizeType.UK     | Sex.FEMALE | 25.67d | 10.17d | 40.5d  | 7.5d   | 9.5d
        8.5d   | ShoeSizeType.US     | Sex.MALE   | 25.67d | 10.17d | 40.5d  | 7.5d   | 8.5d
        9.5d   | ShoeSizeType.US     | Sex.FEMALE | 25.67d | 10.17d | 40.5d  | 7.5d   | 9.5d

        26     | ShoeSizeType.CM     | Sex.MALE   | 26     | 10.33d | 41     | 8      | 9
        26     | ShoeSizeType.CM     | Sex.FEMALE | 26     | 10.33d | 41     | 8      | 10
        10.33d | ShoeSizeType.INCHES | Sex.MALE   | 26     | 10.33d | 41     | 8      | 9
        10.33d | ShoeSizeType.INCHES | Sex.FEMALE | 26     | 10.33d | 41     | 8      | 10
        41     | ShoeSizeType.EU     | Sex.MALE   | 26     | 10.33d | 41     | 8      | 9
        41     | ShoeSizeType.EU     | Sex.FEMALE | 26     | 10.33d | 41     | 8      | 10
        8      | ShoeSizeType.UK     | Sex.MALE   | 26     | 10.33d | 41     | 8      | 9
        8      | ShoeSizeType.UK     | Sex.FEMALE | 26     | 10.33d | 41     | 8      | 10
        9      | ShoeSizeType.US     | Sex.MALE   | 26     | 10.33d | 41     | 8      | 9
        10     | ShoeSizeType.US     | Sex.FEMALE | 26     | 10.33d | 41     | 8      | 10
    }

    @Unroll("value: #value, type: #type, sex: #sex")
    def "ShoeSize objects with the same size should be equal"() {
        given:
        def expectedShoeSize = ShoeSize.builder()
                .value(BigDecimal.valueOf(25.33d))
                .type(ShoeSizeType.CM)
                .sex(Sex.MALE)
                .build()

        when:
        def currentSoeSize = ShoeSize.builder()
                .value(BigDecimal.valueOf(value))
                .type(type)
                .sex(sex)
                .build()

        then:
        expectedShoeSize == currentSoeSize

        where:
        value  | type                | sex
        25.33d | ShoeSizeType.CM     | Sex.MALE
        25.33d | ShoeSizeType.CM     | Sex.FEMALE
        10     | ShoeSizeType.INCHES | Sex.MALE
        10     | ShoeSizeType.INCHES | Sex.FEMALE
        40     | ShoeSizeType.EU     | Sex.MALE
        40     | ShoeSizeType.EU     | Sex.FEMALE
        7      | ShoeSizeType.UK     | Sex.MALE
        7      | ShoeSizeType.UK     | Sex.FEMALE
        8      | ShoeSizeType.US     | Sex.MALE
        9      | ShoeSizeType.US     | Sex.FEMALE
    }

    @Unroll("value: #value, type: #type, sex: #sex")
    def "should not create ShoeSize when invalid values"() {
        when:
        ShoeSize.builder()
                .value(value)
                .type(type)
                .sex(sex)
                .build()

        then:
        thrown(InvalidShoeSizeDataException)

        where:
        value                  | type            | sex
        BigDecimal.valueOf(0)  | ShoeSizeType.EU | Sex.MALE
        BigDecimal.valueOf(-1) | ShoeSizeType.EU | Sex.MALE
        null                   | ShoeSizeType.EU | Sex.MALE
        BigDecimal.valueOf(40) | null            | Sex.MALE
        BigDecimal.valueOf(40) | ShoeSizeType.EU | null
    }
}
