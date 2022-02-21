package org.test.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.coffeemaker.entity.CoffeeMakerActionLog;

public interface CoffeeMakerActionLogRepository extends JpaRepository<CoffeeMakerActionLog, Long> {
}