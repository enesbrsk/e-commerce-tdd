package com.demo.item.domain.enums;

import jakarta.persistence.criteria.CriteriaBuilder;

public enum CategoryType {

    DIGITAL_ITEM(7889 ),
    DEFAULT_ITEM_ELECTRONICS(3004),
    DEFAULT_ITEM_FURNITURE(1001 )

    ;

    public final Integer value;

    private CategoryType(Integer value) {
        this.value = value;
    }

}
