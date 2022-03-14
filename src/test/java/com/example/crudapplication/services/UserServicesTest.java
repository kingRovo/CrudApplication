package com.example.crudapplication.services;

import com.example.crudapplication.model.User;
import com.example.crudapplication.user_Repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Slf4j
class UserServicesTest {



    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServices userServices;



    private static User getUser(){
        User user = new User();

        user.setId(1);
        user.setName("sample1");
        user.setCity("agra");

        return user;

    }

    @Test
    void testAddNewUser() {

        User testUser = getUser();

        testUser.setName("sample1");
        when(userRepo.save(any(User.class))).thenReturn(testUser);

        User created = userServices.addNewUser(testUser);
        assertThat(created.getName()).isSameAs(testUser.getName());

        log.info("Test Passed! user inserted...");

    }

    @Test
    void testDisplayAll() {

        List<User> list = new ArrayList<User>();
        User user1 = new User(1,"sample1","city1");
        User user2 = new User(1,"sample2","city2");
        User user3 = new User(1,"sample3","city3");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userRepo.findAll()).thenReturn(list);

        List<User> userList = userServices.displayAll();
        assertNotNull(userList);

        log.info("all user data display");

    }

    @Test
    void testDisplayByID() {

        User testUser = getUser();
        when(this.userRepo.getById(ArgumentMatchers.anyInt())).thenReturn(getUser());
//
//        User expected = userServices.displayByID(ArgumentMatchers.anyInt());
          assertNotNull(userServices.displayByID(testUser.getId()));

//        assertThat(expected.getName()).isSameAs(testUser.getName());


    }

    @Test
    void testEditUser() {

        User testUser = getUser();
        when(userRepo.getById(ArgumentMatchers.anyInt())).thenReturn(new User());

        User expected = userServices.editUser(testUser.getId(),testUser);

        assertThat(expected.getId()).isSameAs(testUser.getId());

    }

    @Test
    void testDeleteUser() throws Exception {

        User testUser = getUser();
        when(userRepo.getById(ArgumentMatchers.anyInt())).thenReturn(testUser);

        when(userServices.deleteUser(anyInt()).thenThrow(new IllegalStateException()));

        //assertNotNull(userServices.deleteUser(ArgumentMatchers.anyInt()));

        assertThrows(Exception.class,() ->userServices.deleteUser(12));

    }

    @Test
    void testUpdateUserName() {

        User testUser = getUser();
        when(userRepo.getById(ArgumentMatchers.anyInt())).thenReturn(testUser);
        assertNotNull(userServices.updateUserName(ArgumentMatchers.anyInt(),testUser));
    }


}