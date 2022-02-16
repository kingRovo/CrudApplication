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
public ResponseEntity<String> addUser(@RequestBody(required = true) User user){

    try{
    if (user.getName().matches("[a-zA-Z]+")&&user!=null){

    return  new ResponseEntity<>(userServices.addNewUser(user),HttpStatus.CREATED);}
    else {
        return new ResponseEntity<>("invalid name",HttpStatus.BAD_REQUEST);
    }}
    catch (Exception e){

        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

    }
}

@GetMapping("/users")
public ResponseEntity<List<User>> displayAll(){

    try{
        return new ResponseEntity(userServices.displayAll(), HttpStatus.OK);
    }
    catch(Exception e){

        return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
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

        return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

    }

}


@PutMapping("/edit/{id}")
public ResponseEntity<String> editUser(@PathVariable("id") Integer id,@RequestBody User user){

    try{
        if(userServices.displayByID(id)==null){
            return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity(userServices.editUser(id,user),HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}

@DeleteMapping("delete/{id}")
public  ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){

    try {
        if(userServices.displayByID(id)==null){
            return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
        }



        return new ResponseEntity<>(userServices.deleteUser(id),HttpStatus.OK);
    }
    catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}


    @PatchMapping("/updateName/{id}")
    public ResponseEntity UpdateUserName(@PathVariable("id") Integer id,@RequestBody User user){

        try{
            if(userServices.displayByID(id)==null){
                return new ResponseEntity("User not found",HttpStatus.NOT_FOUND);
            }

            return  new ResponseEntity(userServices.updateUserName(id,user),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }


}
