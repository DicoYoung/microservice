package com.example.consumerfeign9003.controller;

import com.example.consumerfeign9003.bean.User;
import com.example.consumerfeign9003.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/consumer/user/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/consumer/user/findUserById/{id}")
    public User findUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    @RequestMapping("/consumer/user/selectByUsername/{username}")
    public User selectByUsername(@PathVariable("username") String username){
        return userService.selectByUsername(username);
    }

    @RequestMapping("/consumer/user/delete/{id}")
    public int delete(@PathVariable("id") int id){
        return userService.delete(id);
    }

    @RequestMapping("/consumer/user/add/{loginName}/{username}/{password}")
    public void add(@PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password) {
        userService.add(loginName,username,password);
    }

    @RequestMapping("/consumer/user/update/{id}/{loginName}/{username}/{password}")
    public int update(@PathVariable("id") int id, @PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password) {
        return userService.update(id,loginName,username,password);
    }

}
