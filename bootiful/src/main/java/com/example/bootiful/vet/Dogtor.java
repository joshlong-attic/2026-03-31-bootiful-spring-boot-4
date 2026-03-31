package com.example.bootiful.vet;

import com.example.bootiful.dogs.DogAdoptedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class Dogtor {

    @ApplicationModuleListener
    void onDogAdopted(DogAdoptedEvent adoptedEvent) throws Exception {
        Thread.sleep(5_000);
        IO.println("adopted " + adoptedEvent);
    }
}
