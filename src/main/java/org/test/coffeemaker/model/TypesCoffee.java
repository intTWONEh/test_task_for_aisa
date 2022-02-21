package org.test.coffeemaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypesCoffee {
    AMERICAN(30, 80, 0), CAPPUCCINO(40, 50, 30), TEST(1,1,1);

    private final int coffeeBeans;
    private final int water;
    private final int milk;
}
