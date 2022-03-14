package com.example.crudapplication.services;

import com.example.crudapplication.model.User;
import com.example.crudapplication.user_Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;



@Service
@Slf4j
public class UserServices {

    @Autowired
    private UserRepo userRepo;


    public User addNewUser(User user) {


        if (!ObjectUtils.isEmpty(user)) {
            log.error("Insert valid user...");


            userRepo.save(user);

            log.info("new User -{} inserted " + user);


        }
        return user;

    }

    public List<User> displayAll() {


        log.info("Displayed all Users ");

        return userRepo.findAllUser();

    }

    public User displayByID(Integer id) {


            return userRepo.getById(id);


    }

    public User editUser(Integer id, User user) {


            userRepo.editUser(id, user.getName(), user.getCity());

            log.info("User city updated to {}" + user.getCity());

            return user;


    }

    public User deleteUser(Integer id) throws Exception
    {


          User user = userRepo.getById(id);

          try{

              userRepo.delete(user);

          }

          catch (Exception exception){
              throw new Exception();
          }


          return user;


    }

    public User updateUserName(Integer id, User user) {


           User user1 = userRepo.getById(id);
            if (user!=null) {
                userRepo.editName(id, user.getName());
                log.info("User Name edited to " + user.getName());
            }
            return user1;


    }

}
