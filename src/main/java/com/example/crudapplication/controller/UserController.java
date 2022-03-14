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
@RequestMapping("api/v1/users")
public class UserController {


    private UserServices userServices;

    @Autowired
    UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody User user){

        try {

            return new ResponseEntity<>(userServices.addNewUser(user), HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> displayAll() {


        try {
            return new ResponseEntity(userServices.displayAll(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUser(@PathVariable("id") Integer id) {

        try {

            return new ResponseEntity(userServices.displayByID(id), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Integer id, @RequestBody User user) {


        try {

            return new ResponseEntity(userServices.editUser(id, user), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {

        try {

            return new ResponseEntity<>(userServices.deleteUser(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @PatchMapping("/{id}")
    public ResponseEntity UpdateUserName(@PathVariable("id") Integer id, @RequestBody User user) {

        try {

            return new ResponseEntity(userServices.updateUserName(id, user), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
