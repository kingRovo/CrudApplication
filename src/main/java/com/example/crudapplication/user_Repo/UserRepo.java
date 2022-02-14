package com.example.crudapplication.user_Repo;

import com.example.crudapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {



    @Query(value = "select user from User user")
    public List<User>  findAllUser();



    @Query(value = "Select  user from User user where user.id =  ?1")
    public User findByUserID(Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from User user where user.id = ?1")
    public void deleteUser(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update User user set user.name = ?2,user.city = ?3  where user.id = ?1")
    public void editUser(Integer id,String name, String city);

    @Modifying
    @Transactional
    @Query(value = "update User user set user.name = ?2 where user.id = ?1")
    public void editName(Integer id,String name);




}
