package com.example.consumerfeign9003.service;

import com.example.consumerfeign9003.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("provider")
@Service
public interface UserService {
    @RequestMapping(value = "/user/findAll", method = RequestMethod.GET)
    List<User> findAll();

    @RequestMapping(value = "/user/findUserById/{id}", method = RequestMethod.GET)
    User findUserById(@PathVariable("id") int id);

    @RequestMapping(value = "/user/selectByUsername/{username}",method = RequestMethod.GET)
    User selectByUsername(@PathVariable("username") String username);

    @RequestMapping(value = "/user/delete/{id}",method = RequestMethod.GET)
    int delete(@PathVariable("id") int id);

    @RequestMapping(value = "/user/add/{loginName}/{username}/{password}",method = RequestMethod.GET)
    void add(@PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password);

    @RequestMapping(value = "/user/update/{id}/{loginName}/{username}/{password}",method = RequestMethod.GET)
    int update(@PathVariable("id") int id, @PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password);

}
