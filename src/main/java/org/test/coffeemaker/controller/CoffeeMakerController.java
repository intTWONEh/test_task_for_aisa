package org.test.coffeemaker.controller;

import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;
import org.test.coffeemaker.entity.CoffeeMakerActionLog;
import org.test.coffeemaker.model.CoffeeMakerStatus;
import org.test.coffeemaker.model.TypesCoffee;
import org.test.coffeemaker.repository.CoffeeMakerActionLogRepository;
import org.test.coffeemaker.service.CoffeeMakerService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coffee-maker/")
public class CoffeeMakerController {
    private final CoffeeMakerService coffeeMakerService;
    private final CoffeeMakerActionLogRepository coffeeMakerActionLogRepository;

    public CoffeeMakerController(CoffeeMakerService coffeeMakerService, CoffeeMakerActionLogRepository coffeeMakerActionLogRepository) {
        this.coffeeMakerService = coffeeMakerService;
        this.coffeeMakerActionLogRepository = coffeeMakerActionLogRepository;
    }

    @ApiOperation(value = "Get log coffee maker")
    @GetMapping("/show-log")
    public List<CoffeeMakerActionLog> getLog(){
        return coffeeMakerActionLogRepository.findAll();
    }

    @ApiOperation(value = "Get status coffee maker")
    @GetMapping("/status")
    public CoffeeMakerStatus getStatus(){
        return coffeeMakerService.getCoffeeMakerStatus();
    }

    @ApiOperation(value = "On/Off coffee maker")
    @GetMapping("on-off")
    public CoffeeMakerStatus OnOffCoffeeMaker(){
        return coffeeMakerService.OnOffCoffeeMaker();
    }

    @ApiOperation(value = "Get what types of coffee the coffee machine can make")
    @GetMapping("/types-coffees")
    public List<TypesCoffee> getTypesCoffees(){
        return coffeeMakerService.getWhatCanCook();
    }

    @ApiOperation(value = "Make coffee")
    @GetMapping("/create-coffee/{typeCoffee}")
    public List<String> getCoffee(@PathVariable TypesCoffee typeCoffee){
        return coffeeMakerService.getCoffee(typeCoffee);
    }

    @ApiOperation(value = "Add water and milk and coffee beans")
    @PostMapping("/resources")
    public Map<String, Integer> addResources(@RequestBody Map<String, Integer> resourceList) {
        return coffeeMakerService.addResources(
                resourceList.get("water"),
                resourceList.get("milk"),
                resourceList.get("coffeeBeans")
        );
    }

    @ApiOperation(value = "Add water or milk or coffee beans")
    @PostMapping("/resources/{resourceName}")
    public Map<String, Integer> addResource(@ApiParam(value = "water or milk or coffee-beans", required = true) @PathVariable String resourceName, @RequestBody Integer quantity){
        switch (resourceName) {
            case ("water"):
                return coffeeMakerService.addWater(quantity);
            case ("milk"):
                return coffeeMakerService.addMilk(quantity);
            case ("coffee-beans"):
                return coffeeMakerService.addCoffeeBeans(quantity);
            default:
                return Collections.singletonMap("no such resource", null);
        }
    }

    @ApiOperation(value = "Get status coffee maker")
    @GetMapping("/resources")
    public Map<String, Integer> getResourcesStatus(){
        return coffeeMakerService.getResourcesStatus();
    }

    @ApiOperation(value = "Get status resource")
    @GetMapping("/resources/{resourceName}")
    public Map<String, Integer> getStatusResource(@ApiParam(value = "water or milk or coffee-beans", required = true) @PathVariable String resourceName){
        switch (resourceName) {
            case ("water"):
                return Collections.singletonMap("water", coffeeMakerService.getStatusWater());
            case ("milk"):
                return Collections.singletonMap("milk", coffeeMakerService.getStatusMilk());
            case ("coffee-beans"):
                return Collections.singletonMap("coffeeBeans", coffeeMakerService.getStatusCoffeeBeans());
            default:
                return Collections.singletonMap("no such resource", null);
        }
    }

    @ApiOperation(value = "Get capacity resources")
    @GetMapping("/resources/capacity")
    public Map<String, Integer> getCapacityResources(){
        return coffeeMakerService.getCapacityResources();
    }

    @ApiOperation(value = "Get capacity resource")
    @GetMapping("/resources/capacity/{resourceName}")
    public Map<String, Integer> getCapacityResource(@ApiParam(value = "water or milk or coffee-beans", required = true)  @PathVariable String resourceName){
        switch (resourceName) {
            case ("water"):
                return Collections.singletonMap("water", coffeeMakerService.getCapacityWater());
            case ("milk"):
                return Collections.singletonMap("milk", coffeeMakerService.getCapacityMilk());
            case ("coffee-beans"):
                return Collections.singletonMap("coffeeBeans", coffeeMakerService.getCapacityCoffeeBeans());
            default:
                return Collections.singletonMap("no such resource", null);
        }
    }
}