package com.example.bootiful.dogs;

import org.springframework.data.repository.ListCrudRepository;

// look mom,no Lombok!
interface DogRepository extends ListCrudRepository<Dog, Integer> {
}
