package com.example.crudapplication.services;
import com.example.crudapplication.model.User;
import com.example.crudapplication.user_Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;



    public  String addNewUser(User user){

        userRepo.save(user);

        return "New User Inserted !";


    }

    public List<User> displayAll(){
        return userRepo.findAllUser();
    }

    public User displayByID(Integer id){

        return userRepo.findById(id).orElseThrow();
    }

    public String editUser(Integer id ,User user){


       userRepo.editUser(id,user.getName(),user.getCity());

       return  "User update Successfully";

    }

    public  String deleteUser(Integer id ){

        userRepo.deleteUser(id);

        return "User Deleted";
    }

    public String updateUserName(Integer id ,User user){


        userRepo.editName(id,user.getName());


        return "User name updated";
    }

}
