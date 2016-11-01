package com.theironyard.controllers;

import com.theironyard.entities.Dog;
import com.theironyard.entities.User;
import com.theironyard.services.DogRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Troy on 10/31/16.
 */
@Controller
public class LostAndHoundController {
    @Autowired
    DogRepository dogs;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = users.findFirstByName(name);
        Iterable<Dog> dogList = dogs.findAll();
        for (Dog dog : dogList) {
            dog.isMe = dog.user.name.equals(name);
        }
        model.addAttribute("dogs",dogList);
        model.addAttribute("user",user);
        return "home";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(String username,String password,HttpSession session) throws Exception {
        User user = users.findFirstByName(username);
        if (user == null) {
            user = new User(username, PasswordStorage.createHash(password));
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(password, user.password)) {
            throw new Exception("Wrong password!");
        }
        session.setAttribute("username",username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout",method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/dog",method = RequestMethod.POST)
    public String addDog(String name,String type,String color,String image,HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        if (user == null) {
            throw new Exception("You can't do that");
        }
        Dog dog = new Dog(name,type,color,image,user);
        dogs.save(dog);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-dog",method = RequestMethod.POST)
    public String delete(int id,HttpSession session) throws Exception {
        if (!validateUser(id,session)) {
            throw new Exception("You can't do that");
        }
        dogs.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit-dog",method = RequestMethod.GET)
    public String editGet(Model model,int id) {
        Dog dog = dogs.findOne(id);
        model.addAttribute("dog",dog);
        return "edit";
    }

    @RequestMapping(path = "/edit-dog",method = RequestMethod.POST)
    public String editPost(int id,String name,String type,String color,String image,HttpSession session) throws Exception {
        if (!validateUser(id,session)) {
            throw new Exception("You can't do that!");
        }
        Dog dog = dogs.findOne(id);
        dog.name = name;
        dog.type = type;
        dog.color = color;
        dog.image = image;
        dogs.save(dog);
        return "redirect:/";
    }

    public boolean validateUser(int id,HttpSession session) {
        //Thanks zach
        String name = (String) session.getAttribute("username");
        User user = users.findFirstByName(name);
        Dog dog = dogs.findOne(id);
        return user != null && dog != null && user.name.equals(dog.user.name);
    }
}
