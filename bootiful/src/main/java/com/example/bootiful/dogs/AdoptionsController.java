package com.example.bootiful.dogs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Transactional
@Controller
@ResponseBody
class AdoptionsController {

    private final DogRepository repository;

    private final ApplicationEventPublisher publisher;

    AdoptionsController(DogRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @PostMapping("/dogs/{dogId}/adoptions")
    void adopt(@PathVariable int dogId, @RequestParam String owner) {
        this.repository.findById(dogId).ifPresent(dog -> {
            var updated = this.repository.save(
                    new Dog(dog.id(), dog.name(), owner, dog.description()));
            IO.println("adopted " + updated);
            this.publisher.publishEvent(new DogAdoptedEvent(dog.id()));
        });

    }
}
