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
public ResponseEntity addUser(@RequestBody(required = true) User user){

    if (user.getName().matches("[a-zA-Z]+")){
    userServices.addNewUser(user);
    return  new ResponseEntity<>(HttpStatus.CREATED);}
    else {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/users")
public ResponseEntity<List<User>> displayAll(){

    try{
        return new ResponseEntity(userServices.displayAll(), HttpStatus.OK);
    }
    catch(Exception e){

        return  new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/user/{id}")
public ResponseEntity<Optional<User>> findUser(@PathVariable("id") Integer id){

    try{

        if (userServices.displayByID(id)==null){

            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity(userServices.displayByID(id),HttpStatus.OK);

    }
    catch (Exception e){

        return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);

    }

}


@PutMapping("/edit/{id}")
public ResponseEntity editUser(@PathVariable("id") Integer id,@RequestBody User user){

    try{
        if(findUser(id)==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userServices.editUser(id,user);
        return  new ResponseEntity(HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
    }

}

@DeleteMapping("delete/{id}")
public  ResponseEntity deleteUser(@PathVariable("id") Integer id){

    try {
        if(findUser(id)==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        userServices.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


    @PatchMapping("/updateName/{id}")
    public ResponseEntity UpdateUserName(@PathVariable("id") Integer id,@RequestBody User user){

        try{
            if(findUser(id)==null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            userServices.updateUserName(id,user);
            return  new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}
