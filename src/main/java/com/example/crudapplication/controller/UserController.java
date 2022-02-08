package com.example.crudapplication.controller;

import com.example.crudapplication.model.User;
import com.example.crudapplication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<List<User>> displayAll(){

    try{
        return new ResponseEntity(userServices.displayAll(), HttpStatus.OK);
    }
    catch(Exception e){

        return  new ResponseEntity(e.getMessage(),HttpStatus.NO_CONTENT);
    }
}

@GetMapping("/user/{id}")
public ResponseEntity<Optional<User>> findUser(@PathVariable("id") Integer id){
    try{
        return  new ResponseEntity<>(userServices.displayByID(id),HttpStatus.OK);
    }
    catch (Exception e){

        return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
    }

}


@PutMapping("/edit/{id}")
public ResponseEntity editUser(@PathVariable("id") Integer id,@RequestBody User user){

    try{
        userServices.editUser(id,user);
        return  new ResponseEntity(HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}

@DeleteMapping("delete/{id}")
public  ResponseEntity deleteUser(@PathVariable("id") Integer id){

    try {
        userServices.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}





}
