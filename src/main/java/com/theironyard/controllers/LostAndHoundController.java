package com.theironyard.controllers;

import com.theironyard.services.DogRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Troy on 10/31/16.
 */
@Controller
public class LostAndHoundController {
    @Autowired
    DogRepository dogs;

    @Autowired
    UserRepository users;
}
