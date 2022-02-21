package org.test.coffeemaker.model.test;

import java.util.HashMap;
import java.util.Map;

public class TypeCoffee {
    private String name;
    private Map<Ingredient, Integer> ingredients;
    private TypeCoffee(){
        this.name = "";
        this.ingredients = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public static class Builder {
        private TypeCoffee typeCoffee;

        public Builder() {
            typeCoffee = new TypeCoffee();
        }

        public Builder setName(final String name){
            typeCoffee.name = name.toLowerCase();
            return this;
        }

        public Builder addIngredient(final Ingredient ingredient, final Integer quantity){
            typeCoffee.ingredients.put(ingredient, quantity);
            return this;
        }

        public TypeCoffee build(){
            return typeCoffee;
        }
    }
}
