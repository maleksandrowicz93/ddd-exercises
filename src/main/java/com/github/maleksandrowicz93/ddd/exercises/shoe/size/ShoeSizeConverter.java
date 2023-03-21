package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import java.math.BigDecimal;

interface ShoeSizeConverter {

    BigDecimal toEu(BigDecimal shoeSize);
    BigDecimal fromEu(BigDecimal shoeSize);
}
