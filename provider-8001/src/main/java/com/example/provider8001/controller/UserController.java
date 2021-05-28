package com.example.provider8001.controller;

import com.example.provider8001.domain.User;
import com.example.provider8001.service.UserServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    // 注入UserService
    @Resource
    private UserServiceImpl userService;

    @Autowired
    private DiscoveryClient client;

    //查询1
    @RequestMapping("/user/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    //查询2
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/user/findUserById/{id}")
    public User findUserById(@PathVariable("id") int id) throws Exception {
        User user = userService.findUserById(id);
        if(user == null) throw new Exception();
        else return user;
    }
    public User fallback(@PathVariable("id") int id){
        User user = new User();
        user.setId(id);
        user.setLoginName("The user id " + id + " is not found!");
        user.setDbSource("no this data in Database");
        return user;
    }

    //discovery方法
    @RequestMapping(value = "/user/discovery", method = RequestMethod.GET)
    public Object discovery()
    {
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("provider");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

    //查询3
    @RequestMapping("/user/selectByUsername/{username}")
    public User findUserByName(@PathVariable("username") String username){
        System.out.println(userService.selectByUsername(username).getDbSource());
        return userService.selectByUsername(username);
    }

    //删除
    @RequestMapping("/user/delete/{id}")
    public int delete(@PathVariable("id") int id) throws Exception {
        Integer idUpper = id;
        User user = userService.findUserById(id);
        if(user == null) throw new Exception();
        else
            return userService.delete(idUpper);
    }

    //自动维护主键的增加
    @RequestMapping("/user/add/{loginName}/{username}/{password}")
    public void add(@PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        User e = new User();
        e.setLoginName(loginName);
        e.setUsername(username);
        e.setPassword(password);
        e.setDbSource("mybatis");
        if(e.getLoginName() == null) throw new Exception();
        else
            userService.insertGetKey(e);
    }

    //更改,根据ID
    @RequestMapping("/user/update/{id}/{loginName}/{username}/{password}")
    public int update(@PathVariable("id") int id, @PathVariable("loginName") String loginName, @PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        User e = userService.findUserById(id);
        if(e == null) throw new Exception();
        else
            e.setLoginName(loginName);
        e.setUsername(username);
        e.setPassword(password);
        return userService.update(e);
    }
}
