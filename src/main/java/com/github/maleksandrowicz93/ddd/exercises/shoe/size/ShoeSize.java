package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Objects;

import static com.github.maleksandrowicz93.ddd.exercises.shoe.size.InvalidShoeSizeDataCause.*;

@FieldDefaults(makeFinal = true)
class ShoeSize {

    BigDecimal euValue;
    Sex sex;

    @Builder(access = AccessLevel.PACKAGE)
    ShoeSize(BigDecimal value, ShoeSizeType type, Sex sex) {
        if (value == null || type == null || sex == null) {
            throw new InvalidShoeSizeDataException(NULL_VALUES);
        }
        if (value.compareTo(BigDecimal.ONE) < 0) {
            throw new InvalidShoeSizeDataException(TOO_SMALL_SIZE);
        }
        this.sex = sex;
        BigDecimal euValue = type.toEu(value);
        this.euValue = ShoeSizeType.US == type
                ? this.sex.toEu(euValue)
                : euValue;
    }

    BigDecimal inCm() {
        return ShoeSizeType.CM.fromEu(euValue);
    }

    BigDecimal inInches() {
        return ShoeSizeType.INCHES.fromEu(euValue);
    }

    BigDecimal toEu() {
        return euValue;
    }

    BigDecimal toUk() {
        return ShoeSizeType.UK.fromEu(euValue);
    }

    BigDecimal toUs() {
        return sex.fromEu(ShoeSizeType.US.fromEu(euValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoeSize shoeSize)) return false;
        return euValue.equals(shoeSize.euValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(euValue);
    }
}
