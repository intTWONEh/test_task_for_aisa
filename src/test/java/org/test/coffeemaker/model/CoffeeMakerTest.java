package org.test.coffeemaker.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeMakerTest {

    @DisplayName("Test on/off coffee maker")
    @Test
    void buttonOnOff() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        assertEquals(CoffeeMakerStatus.OFF, coffeeMaker.coffeeMakerStatus());

        coffeeMaker.buttonOnOff();
        assertEquals(CoffeeMakerStatus.ON, coffeeMaker.coffeeMakerStatus());

        coffeeMaker.buttonOnOff();
        assertEquals(CoffeeMakerStatus.OFF, coffeeMaker.coffeeMakerStatus());
    }

    @DisplayName("Test create Coffee")
    @Test
    void createCoffee() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        assertEquals(CoffeeMakerResultMessages.COFFEE_MAKER_OFF, coffeeMaker.createCoffee(TypesCoffee.AMERICAN).get(0));

        coffeeMaker.buttonOnOff();
        assertEquals(CoffeeMakerResultMessages.COFFEE_MAKER_DOESNT_KNOW, coffeeMaker.createCoffee(TypesCoffee.TEST).get(0));

        assertEquals(Arrays.asList(
                CoffeeMakerResultMessages.NEED_COFFEE_BEANS,
                CoffeeMakerResultMessages.NEED_WATER
        ),coffeeMaker.createCoffee(TypesCoffee.AMERICAN));

        coffeeMaker.addCoffeeBeans(100);
        coffeeMaker.addWater(100);

        assertEquals(CoffeeMakerResultMessages.COFFEE_READY,coffeeMaker.createCoffee(TypesCoffee.AMERICAN).get(0));
    }

    @DisplayName("Test add Water")
    @Test
    void addWater() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        assertEquals(0 , coffeeMaker.addWater(-100));

        assertEquals(500 , coffeeMaker.addWater(1500));

        assertEquals(1500 , coffeeMaker.addWater(1500));
    }

    @DisplayName("Test add Milk")
    @Test
    void addMilk() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        assertEquals(0 , coffeeMaker.addMilk(-100));

        assertEquals(1000 , coffeeMaker.addMilk(1500));

        assertEquals(1500 , coffeeMaker.addMilk(1500));
    }

    @DisplayName("Test add Coffee")
    @Test
    void addCoffeeBeans() {
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        assertEquals(0 , coffeeMaker.addCoffeeBeans(-100));

        assertEquals(1200 , coffeeMaker.addCoffeeBeans(1500));

        assertEquals(1500 , coffeeMaker.addCoffeeBeans(1500));
    }
}