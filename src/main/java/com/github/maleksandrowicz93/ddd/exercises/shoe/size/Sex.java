package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import java.math.BigDecimal;

enum Sex implements ShoeSizeConverter {
    MALE {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            return shoeSize;
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return shoeSize;
        }
    },
    FEMALE {
        @Override
        public BigDecimal toEu(BigDecimal shoeSize) {
            return shoeSize.subtract(BigDecimal.ONE);
        }

        @Override
        public BigDecimal fromEu(BigDecimal shoeSize) {
            return shoeSize.add(BigDecimal.ONE);
        }
    };
}
