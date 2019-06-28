
package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("getuser")
    public List<User> getUser(String name){
        List<User> list = userService.findByName(name);
        return list;
    }

    @RequestMapping("adduser")
    public User addUser(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号 
        uuid = uuid.replaceAll("-", "");
        User user = new User();
        user.setId(uuid);
        user.setName("name");
        user.setAge(18);
        user.setCreateTime(new Date());
        user = userRepository.save(user);
        return user;
    }

    @RequestMapping("deluser")
    public String delUser(String id){
        userRepository.deleteById(id);
        return "删除成功";
    }
}