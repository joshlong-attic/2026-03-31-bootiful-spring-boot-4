package com.example.bootiful.cats;

import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.registry.ImportHttpServices;

import java.util.concurrent.atomic.AtomicInteger;

@ImportHttpServices(com.example.bootiful.cats.CatsClient.class)
@Controller
@ResponseBody
class CatFactsController {

    private final CatsClient client;

    private final AtomicInteger counter = new AtomicInteger(0);

    CatFactsController(CatsClient client) {
        this.client = client;
    }

    @ConcurrencyLimit(10)
    @Retryable(maxRetries = 5, includes = IllegalStateException.class)
    @GetMapping("/cats")
    CatFacts facts() {
        if (this.counter.incrementAndGet() < 4) {
            IO.println("oops!");
            throw new IllegalStateException("boom");
        }
        IO.println("yay!");
        return client.facts();
    }
}
