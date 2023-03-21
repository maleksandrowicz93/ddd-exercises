package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

@Getter
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
enum ShoeSizeType implements ShoeSizeRounder, ShoeSizeConverter {
    CM(RoundingStrategy.EVERY_ONE_THIRD) {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            return RoundingStrategy.EVERY_ONE_SECOND.round(shoeSize
                    .multiply(BigDecimal.valueOf(3))
                    .divide(BigDecimal.valueOf(2), new MathContext(3))
                    .add(BigDecimal.valueOf(2)));
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return round(shoeSize
                    .subtract(BigDecimal.valueOf(2))
                    .multiply(BigDecimal.valueOf(2))
                    .divide(BigDecimal.valueOf(3), new MathContext(4)));
        }
    },
    INCHES(RoundingStrategy.EVERY_ONE_SIXTH) {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            BigDecimal uk = shoeSize
                    .subtract(BigDecimal.valueOf(8))
                    .multiply(BigDecimal.valueOf(3))
                    .add(BigDecimal.ONE);
            BigDecimal round = RoundingStrategy.EVERY_ONE_SECOND.round(uk);
            return UK.toEu(round);
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            BigDecimal uk = UK.fromEu(shoeSize);
            BigDecimal inches = uk
                    .subtract(BigDecimal.ONE)
                    .divide(BigDecimal.valueOf(3), new MathContext(4))
                    .add(BigDecimal.valueOf(8));
            return round(inches);
        }
    },
    EU {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            return RoundingStrategy.EVERY_ONE_SECOND.round(shoeSize);
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return shoeSize;
        }
    },
    UK {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            BigDecimal converted = shoeSize.add(BigDecimal.valueOf(UK_TO_EU_DIFFERENCE));
            return RoundingStrategy.EVERY_ONE_SECOND.round(converted);
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return shoeSize.subtract(BigDecimal.valueOf(UK_TO_EU_DIFFERENCE));
        }
    },
    US {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            BigDecimal converted = shoeSize.add(BigDecimal.valueOf(UK_TO_EU_DIFFERENCE - 1));
            return RoundingStrategy.EVERY_ONE_SECOND.round(converted);
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return shoeSize.subtract(BigDecimal.valueOf(UK_TO_EU_DIFFERENCE - 1));
        }
    };

    private static final RoundingMode ROUNDING_MODE = HALF_UP;
    private static final int UK_TO_EU_DIFFERENCE = 33;
    RoundingStrategy roundingStrategy;

    ShoeSizeType() {
        roundingStrategy = RoundingStrategy.EVERY_ONE_SECOND;
    }

    @Override
    public BigDecimal round(BigDecimal value) {
        return roundingStrategy.round(value);
    }


    private enum RoundingStrategy implements ShoeSizeRounder {
        EVERY_ONE_SECOND {
            @Override
            public BigDecimal round(BigDecimal value) {
                if (value.signum() == 0) {
                    return value;
                }
                BigDecimal increment = new BigDecimal("0.5");
                BigDecimal divided = value.divide(increment, new MathContext(2));
                BigDecimal multiplied = divided.multiply(increment);
                return multiplied.setScale(1, ROUNDING_MODE);
            }
        },
        EVERY_ONE_THIRD {
            @Override
            public BigDecimal round(BigDecimal value) {
                if (value.signum() == 0) {
                    return value;
                }
                BigDecimal increment = BigDecimal.valueOf(1d / 3d);
                BigDecimal divided = value.divide(increment, new MathContext(2, ROUNDING_MODE));
                BigDecimal multiplied = divided.multiply(increment);
                return multiplied.setScale(2, ROUNDING_MODE);
            }
        },
        EVERY_ONE_SIXTH {
            @Override
            public BigDecimal round(BigDecimal value) {
                if (value.signum() == 0) {
                    return value;
                }
                BigDecimal increment = BigDecimal.valueOf(1d / 6d);
                BigDecimal divided = value.divide(increment, new MathContext(2, ROUNDING_MODE));
                BigDecimal multiplied = divided.multiply(increment);
                return multiplied.setScale(2, ROUNDING_MODE);
            }
        }
    }
}
