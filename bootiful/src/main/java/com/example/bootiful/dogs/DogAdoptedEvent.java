package com.example.bootiful.dogs;

import org.springframework.modulith.events.Externalized;

//@Externalized ("...")
public record DogAdoptedEvent(int dogId) {
}
