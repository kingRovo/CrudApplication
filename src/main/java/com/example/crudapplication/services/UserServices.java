package com.example.crudapplication.services;


import com.example.crudapplication.model.User;
import com.example.crudapplication.user_Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;

    public  void addNewUser(User user){

        userRepo.save(user);
    }

    public List<User> displayAll(){
        return userRepo.findAll();
    }

    public Optional<User> displayByID(Integer id){

        return userRepo.findById(id);
    }

    public void editUser(Integer id ,User user1){


        User user = userRepo.findById(id).orElseThrow();
        user.setName(user1.getName());
        user.setCity(user1.getCity());
        userRepo.save(user);
        //editing user by getter and setter

    }

    public  void deleteUser(Integer id ){

        User user = userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }
}
