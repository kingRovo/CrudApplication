package com.example.crudapplication.controller;

import com.example.crudapplication.model.User;
import com.example.crudapplication.services.UserServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@Import(UserController.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserServices userServices;


    private static final String BASE_CONTEXT_PATH = "http://localhost:8083/api/v1/users";


    /**
     * Get getUserPayload
     * @return string
     */
    private String getUserPayload() {
        return "{\"id\":1,\"name\":\"user1\",\"city\":\"agra\"}";
    }


    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    User getUser(){

        return new User(1,"user1","city1");
    }


    @Test
    void testAddUser() throws Exception {

        final var payload = getUserPayload();
        when(this.userServices.addNewUser(any(User.class))).thenReturn(getUser());
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_CONTEXT_PATH + "/")
                .contentType(MediaType.APPLICATION_JSON).content(payload);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());


    }

    @Test
    void testDisplayAll() throws Exception {

        when(userServices.addNewUser(any())).thenReturn(getUser());
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_CONTEXT_PATH + "/")
                .accept(MediaType.APPLICATION_JSON);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void testFindUser() throws Exception {

        when(userServices.displayByID(anyInt())).thenReturn(getUser());
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_CONTEXT_PATH + "/1")
                .accept(MediaType.APPLICATION_JSON);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }

    @Test
    void testEditUser() throws Exception {

        final var payload =getUserPayload();
        when(userServices.editUser(anyInt(),any())).thenReturn(new User());

        final RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(BASE_CONTEXT_PATH + "/16").contentType(MediaType.APPLICATION_JSON).content(payload);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void testDeleteUser() throws Exception {

        when(userServices.deleteUser(anyInt())).thenReturn(new User());
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_CONTEXT_PATH + "/12").accept(MediaType.APPLICATION_JSON);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void testUpdateUserName() throws Exception {


        when(userServices.updateUserName(anyInt(),any())).thenReturn(new User());
        final var payload = getUserPayload();
        final RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(BASE_CONTEXT_PATH + "/16").contentType(MediaType.APPLICATION_JSON).content(payload);
        final var result = this.mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }
}