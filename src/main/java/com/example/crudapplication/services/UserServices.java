package com.example.crudapplication.services;
import com.example.crudapplication.model.User;
import com.example.crudapplication.user_Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserServices {

    @Autowired
    private UserRepo userRepo;



    public  String addNewUser(User user){


        if (user==null){
            log.error("Insert valid user...");
        }

        userRepo.save(user);

        log.info("new User -{} inserted "+user);
        return "New User Inserted !";


    }

    public List<User> displayAll(){


           log.info("Displayed all Users ");

           return userRepo.findAllUser();

    }

    public User displayByID(Integer id){

        if(userRepo.findById(id).orElseThrow()==null){

            log.error("Insert valid id");
            return null;
        }
        else{
            User user = userRepo.findById(id).orElseThrow();
            log.info("Display User : {} "+user);
            return user;

        }

    }

    public String editUser(Integer id ,User user){

        if(userRepo.findById(id).orElseThrow()==null){

            log.error("Insert valid id");
            return "Invalid Id";

        }
        else {

            userRepo.editUser(id, user.getName(), user.getCity());

            log.info("User city updated to {}"+user.getCity());
            return "User update Successfully";
        }

    }

    public  String deleteUser(Integer id ){

        if(userRepo.findById(id).orElseThrow()==null){

            log.error("Insert valid id");
            return "Invalid Id";

        }
        else {
            userRepo.deleteUser(id);

            log.info("User Deleted at Id : "+id);
            return "User Deleted";

        }
    }

    public String updateUserName(Integer id ,User user){


        if(userRepo.findById(id).orElseThrow()==null){

            log.error("Insert valid id/name");
            return "Invalid Id";

        }
        else{
            userRepo.editName(id,user.getName());

            log.info("User Name edited to "+user.getName());

            return "User name updated";
        }

    }

}
