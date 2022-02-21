package org.test.coffeemaker.service;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Service;
import org.test.coffeemaker.model.CoffeeMaker;
import org.test.coffeemaker.model.CoffeeMakerStatus;
import org.test.coffeemaker.model.TypesCoffee;

import java.util.*;
@ApiModel
@Service
public class CoffeeMakerService {
    private final CoffeeMaker coffeeMaker;

    public CoffeeMakerService(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }

    public CoffeeMakerStatus OnOffCoffeeMaker(){
        coffeeMaker.buttonOnOff();
        return coffeeMaker.coffeeMakerStatus();
    }
    public CoffeeMakerStatus getCoffeeMakerStatus(){
        return coffeeMaker.coffeeMakerStatus();
    }
    public List<TypesCoffee> getWhatCanCook(){
        return coffeeMaker.getTypesCoffees();
    }

    public Map<String, Integer> getResourcesStatus(){
        Map<String, Integer> listResourcesStatus = new HashMap<>();

        listResourcesStatus.put("water", getStatusWater());
        listResourcesStatus.put("coffeeBeans", getStatusCoffeeBeans());
        listResourcesStatus.put("milk", getStatusMilk());

        return listResourcesStatus;
    }
    public Map<String, Integer> getCapacityResources(){
        Map<String, Integer> listCapacityResources = new HashMap<>();

        listCapacityResources.put("Water", getCapacityWater());
        listCapacityResources.put("CoffeeBeans", getCapacityCoffeeBeans());
        listCapacityResources.put("Milk", getCapacityMilk());

        return listCapacityResources;
    }

    public Integer getStatusWater(){
        return coffeeMaker.getWater();
    }
    public Integer getCapacityWater() { return coffeeMaker.getWATER_CAPACITY(); }

    public Integer getStatusCoffeeBeans(){
        return coffeeMaker.getCoffeeBeans();
    }
    public Integer getCapacityCoffeeBeans() { return coffeeMaker.getCOFFEE_BEANS_CAPACITY(); }

    public Integer getStatusMilk(){
        return coffeeMaker.getMilk();
    }
    public Integer getCapacityMilk() { return coffeeMaker.getMILK_CAPACITY(); }

    public Map<String, Integer> addWater(Integer water){
        if(water == null || water <= 0) return Collections.singletonMap("Value cannot be negative or null! (Max value water)", coffeeMaker.getWATER_CAPACITY());

        int remainder = coffeeMaker.addWater(water);
        return remainder > 0 ?
                Collections.singletonMap("After adding " + water +" ml of water left", remainder) :
                Collections.singletonMap("Added water " + water + " ml, more can be added", coffeeMaker.getWATER_CAPACITY() - water);
    }
    public Map<String, Integer> addMilk(Integer milk){
        if(milk == null || milk <= 0) return Collections.singletonMap("Value cannot be negative or null! (Max value milk)", coffeeMaker.getMILK_CAPACITY());

        int remainder = coffeeMaker.addMilk(milk);
        return remainder >= 0 ?
                Collections.singletonMap("After adding " + milk +" ml of milk left", remainder) :
                Collections.singletonMap("Added milk " + milk + " ml, more can be added", coffeeMaker.getMILK_CAPACITY() - milk);
    }
    public Map<String, Integer> addCoffeeBeans(Integer coffeeBeans) {
        if(coffeeBeans == null || coffeeBeans <= 0) return Collections.singletonMap("Value cannot be negative or null! (Max value coffee beans)", coffeeMaker.getCOFFEE_BEANS_CAPACITY());

        int remainder = coffeeMaker.addCoffeeBeans(coffeeBeans);
        return remainder > 0 ?
                Collections.singletonMap("After adding " + coffeeBeans +" g of coffee beans left", remainder) :
                Collections.singletonMap("Added coffee beans " + coffeeBeans + " g, more can be added", coffeeMaker.getCOFFEE_BEANS_CAPACITY() - coffeeBeans);
    }
    public Map<String, Integer> addResources(Integer water, Integer milk, Integer coffeeBeans){
        Map<String, Integer> resultList = new LinkedHashMap<>();

        resultList.putAll(addWater(water));
        resultList.putAll(addMilk(milk));
        resultList.putAll(addCoffeeBeans(coffeeBeans));

        return resultList;
    }

    public List<String> getCoffee(TypesCoffee typeCoffee){
        List<String> result = new LinkedList<>();

        coffeeMaker.createCoffee(typeCoffee).forEach(message -> result.add(message.getDescription()));

        return result;
    }
}
