package com.theironyard.services;

import com.theironyard.entities.Dog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Troy on 10/31/16.
 */
public interface DogRepository extends CrudRepository<Dog,Integer> {
}
