package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Troy on 10/31/16.
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    User findFirstByName(String name);
}
