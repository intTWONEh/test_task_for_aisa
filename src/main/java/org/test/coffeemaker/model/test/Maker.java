package org.test.coffeemaker.model.test;

import java.util.Arrays;
import java.util.List;

//
public class Maker {
    private List<TypeCoffee> typesCoffee = Arrays.asList(
            new TypeCoffee.Builder().setName("Americano")
                    .addIngredient(IngredientList.water, 10)
                    .build()
    );

    private Ingredient water = IngredientList.water;
}
