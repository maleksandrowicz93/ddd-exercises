package com.github.maleksandrowicz93.ddd.exercises.exam.result

import spock.lang.Specification
import spock.lang.Unroll

class ExamResultSpec extends Specification {

    @Unroll("percentage: #percentage, threshold: #threshold, isBelow = #isBelow")
    def "ExamResult created from percentage score should compare score against threshold"() {
        given:
        def examResult = new ExamResult(percentage)

        expect:
        examResult.isBelowThreshold(threshold) == isBelow

        where:
        percentage | threshold | isBelow
        73.5f      | 73.50f    | false
        73.50f     | 73.5f     | false
        73.50f     | 73.50f    | false
        73.5f      | 73.5f     | false
        73.52f     | 73.5f     | false
        73.48f     | 73.5f     | true
        0          | 30        | true
        100        | 100       | false
    }

    @Unroll("score: #score, max: #max, threshold: #threshold, isBelow = #isBelow")
    def "ExamResult created from points score should compare score against threshold"() {
        given:
        def examResult = new ExamResult(score, max)

        expect:
        examResult.isBelowThreshold(threshold) == isBelow

        where:
        score  | max | threshold | isBelow
        73.5f  | 100 | 73.50f    | false
        73.50f | 100 | 73.5f     | false
        73.50f | 100 | 73.50f    | false
        73.5f  | 100 | 73.5f     | false
        73.52f | 100 | 73.5f     | false
        73.48f | 100 | 73.5f     | true
        0      | 10  | 3         | true
        10     | 10  | 10        | false
    }

    @Unroll("#actualResult")
    def "ExamResult instances should be equal if the same score"() {
        expect:
        expectedResult == actualResult

        where:
        expectedResult = new ExamResult(75f)
        actualResult << [
                new ExamResult(75),
                new ExamResult(75.00f),
                new ExamResult(75, 100),
                new ExamResult(75.00f, 100.00f),
                new ExamResult(37.5, 50)
        ]
    }

    @Unroll("#percentage")
    def "ExamResult should not be created from invalid percentage score"() {
        when:
        new ExamResult((float) percentage)

        then:
        thrown(IllegalArgumentException)

        where:
        percentage << ([-0.01f, -1, 100.01f, 101] as Number[])
    }

    @Unroll("score: #score, max: #max")
    def "ExamResult should not be created from invalid points score"() {
        when:
        new ExamResult(score, max)

        then:
        thrown(IllegalArgumentException)

        where:
        score  | max
        -0.01f | 0
        -1     | 0
        1.01f  | 1
        2      | 1
    }

    @Unroll("#threshold")
    def "should not compares score to threshold when invalid threshold"() {
        when:
        new ExamResult(40).isBelowThreshold((float) threshold)

        then:
        thrown(IllegalArgumentException)

        where:
        threshold << ([-0.01f, -1, 100.01f, 101] as Number[])
    }
}
