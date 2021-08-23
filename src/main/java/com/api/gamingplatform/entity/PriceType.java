package com.api.gamingplatform.entity;

import java.util.Arrays;
import java.util.Optional;

public enum PriceType {
    One("One",1),
    Five("Five",5),
    Ten("Ten",10);

    private String text;
    private Integer value;
    PriceType(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public static Optional<PriceType> getFromText(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.text.equalsIgnoreCase(text))
                .findFirst();
    }

    public static Integer getFromPriceType(String pageType) {
        Integer priceType = PriceType.valueOf(pageType).value;
        return priceType;
    }


}
