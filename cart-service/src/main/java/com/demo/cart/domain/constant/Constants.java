package com.demo.cart.domain.constant;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Constants {

    public static final int MAX_TOTAL_QUANTITY = 30;
    public static final BigDecimal MAX_TOTAL_AMOUNT = BigDecimal.valueOf(500000.0);

}
