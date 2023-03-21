package com.github.maleksandrowicz93.ddd.exercises.shoe.size;

import java.math.BigDecimal;

interface ShoeSizeRounder {
    BigDecimal round(BigDecimal value);
}
