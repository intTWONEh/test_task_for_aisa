package org.test.coffeemaker.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CoffeeMaker {
    private CoffeeMakerStatus status = CoffeeMakerStatus.OFF;
    private final List<TypesCoffee> typesCoffees = Arrays.asList(TypesCoffee.AMERICAN, TypesCoffee.CAPPUCCINO);

    @Getter private Integer water = 0;
    @Getter private final Integer WATER_CAPACITY = 1000;

    @Getter private Integer milk = 0;
    @Getter private final Integer MILK_CAPACITY = 500;

    @Getter private Integer coffeeBeans = 0;
    @Getter private final Integer COFFEE_BEANS_CAPACITY = 300;

    public void buttonOnOff(){
        status = (status == CoffeeMakerStatus.OFF) ? CoffeeMakerStatus.ON : CoffeeMakerStatus.OFF;
    }

    public CoffeeMakerStatus coffeeMakerStatus(){
        return status;
    }

    public List<CoffeeMakerResultMessages> createCoffee(TypesCoffee typeCoffee){
        List<CoffeeMakerResultMessages> resultMessages = new LinkedList<>();

        if(status == CoffeeMakerStatus.OFF){
            resultMessages.add(CoffeeMakerResultMessages.COFFEE_MAKER_OFF);
            return resultMessages;
        }

        if (!typesCoffees.contains(typeCoffee)) {
            resultMessages.add(CoffeeMakerResultMessages.COFFEE_MAKER_DOESNT_KNOW);
            return resultMessages;
        }

        if(status == CoffeeMakerStatus.WORKING){
            resultMessages.add(CoffeeMakerResultMessages.COFFEE_MAKER_NOW_WORKING_NEED_WAIT);
            return resultMessages;
        }

        status = CoffeeMakerStatus.WORKING;

        //Проверка достаточно ли ресурсов
        if(typeCoffee.getCoffeeBeans() > coffeeBeans) resultMessages.add(CoffeeMakerResultMessages.NEED_COFFEE_BEANS);
        if(typeCoffee.getMilk() > milk) resultMessages.add(CoffeeMakerResultMessages.NEED_MILK);
        if(typeCoffee.getWater() > water) resultMessages.add(CoffeeMakerResultMessages.NEED_WATER);
        if(resultMessages.size() > 0) {
            status = CoffeeMakerStatus.ON;
            return resultMessages;
        }

        coffeeBeans -= typeCoffee.getCoffeeBeans();
        milk -= typeCoffee.getMilk();
        water -= typeCoffee.getWater();

        status = CoffeeMakerStatus.ON;
        return Collections.singletonList(CoffeeMakerResultMessages.COFFEE_READY);
    }

    public List<TypesCoffee> getTypesCoffees() {
        return typesCoffees;
    }

    public Integer addWater(Integer water){
        if(water <= 0) return 0;
        int remainingSpace = WATER_CAPACITY - this.water;

        if(remainingSpace >= water){
            this.water += water;
            return 0;
        } else {
            this.water += remainingSpace;
            return water - remainingSpace;
        }
    }

    public Integer addMilk(Integer milk){
        if(milk <= 0) return 0;
        int remainingSpace = MILK_CAPACITY - this.milk;

        if(remainingSpace >= milk){
            this.milk += milk;
            return 0;
        } else {
            this.milk += remainingSpace;
            return milk - remainingSpace;
        }
    }

    public Integer addCoffeeBeans(Integer coffeeBeans){
        if(coffeeBeans <= 0) return 0;
        int remainingSpace = COFFEE_BEANS_CAPACITY - this.coffeeBeans;

        if(remainingSpace >= coffeeBeans){
            this.coffeeBeans += coffeeBeans;
            return 0;
        } else {
            this.coffeeBeans += remainingSpace;
            return coffeeBeans - remainingSpace;
        }
    }
}

