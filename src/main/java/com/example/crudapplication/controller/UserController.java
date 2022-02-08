package com.example.crudapplication.controller;


import com.example.crudapplication.model.User;
import com.example.crudapplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {


@Autowired
private UserServices userServices;

@PostMapping("/add")
public void addUser(@RequestBody User user){

    userServices.addNewUser(user);
}

@GetMapping("/users")
public List<User> displayAll(){
    return userServices.displayAll();
}

@GetMapping("/user/{id}")
public Optional<User> findUser(@PathVariable("id") Integer id){
    return userServices.displayByID(id);
}


@PutMapping("/edit/{id}")
public void editUser(@PathVariable("id") Integer id,@RequestBody User user){
    userServices.editUser(id,user);
}

@DeleteMapping("delete/{id}")
public  void deleteUser(@PathVariable("id") Integer id){
    userServices.deleteUser(id);
}





}
