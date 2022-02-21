package org.test.coffeemaker.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.test.coffeemaker.entity.CoffeeMakerActionLog;
import org.test.coffeemaker.repository.CoffeeMakerActionLogRepository;
import java.time.LocalDateTime;

@Aspect
@Component
class CoffeeMakerLogger {
    private final CoffeeMakerActionLogRepository coffeeMakerActionLogRepository;

    public CoffeeMakerLogger(CoffeeMakerActionLogRepository coffeeMakerActionLogRepository) {
        this.coffeeMakerActionLogRepository = coffeeMakerActionLogRepository;
    }

    @Around("execution(* org.test.coffeemaker.service.CoffeeMakerService.*(..))")
    public Object logGet(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var log = new CoffeeMakerActionLog();
        Object result = proceedingJoinPoint.proceed();

        log.setDescription(proceedingJoinPoint.getSignature().getName());
        log.setResult(result.toString());
        log.setActionTime(LocalDateTime.now());
        coffeeMakerActionLogRepository.save(log);

        return result;
    }
}
