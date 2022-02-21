package org.test.coffeemaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoffeeMakerResultMessages {
    NEED_MILK("Need add milk"),
    NEED_WATER("Need add water"),
    NEED_COFFEE_BEANS("Need add coffee beans"),
    COFFEE_MAKER_START_WORKING("The coffee maker has started to work, please wait"),
    COFFEE_MAKER_OFF("Coffee machine off"),
    COFFEE_MAKER_NOW_WORKING_NEED_WAIT("The coffee maker is busy at the moment, please wait until it finishes its work"),
    COFFEE_MAKER_DOESNT_KNOW("This type of coffee the coffee maker does not know how to cook"),
    WRONG("Something went wrong contact support"),
    COFFEE_READY("Coffee is ready");

    private final String description;
}
