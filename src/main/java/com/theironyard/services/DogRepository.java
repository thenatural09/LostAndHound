package com.theironyard.services;

import com.theironyard.entities.Dog;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 10/31/16.
 */
public interface DogRepository extends CrudRepository<Dog,Integer> {
    List<Dog> findByUser(User user);
}
