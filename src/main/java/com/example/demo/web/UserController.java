
package com.example.demo.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.demo.common.JsonResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //重定向页面
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "world");
        return "user/hello";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "user/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/create_edit";
    }

    @GetMapping("/edit")
    public String edit(Model model,String id) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "user/create_edit";
    }


    //指定返回json对象
    @ResponseBody
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public JsonResponse getList(UserDto dto){
        JsonResponse response = new JsonResponse();
        try {
            Page<User> page = userService.findAll(dto);
            response.setData(page.getContent());
            response.setMsg("成功");
            response.setTotal(page.getTotalElements());
        }catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(-1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public JsonResponse create(User model){
        JsonResponse response = new JsonResponse();
        try {
            String uuid = UUID.randomUUID().toString();
            //去掉“-”符号
            uuid = uuid.replaceAll("-", "");
            model.setId(uuid);
            model.setCreateTime(new Date());
            model = userService.save(model);
            response.setData(model);
            response.setMsg("成功");
        }catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(-1);
        }
        return response;

    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public JsonResponse edit(User model){
        JsonResponse response = new JsonResponse();
        try {
            User user = userService.findById(model.getId()).get();
            user.setName(model.getName());
            user.setAge(model.getAge());
            model = userService.save(user);
            response.setData(model);
            response.setMsg("成功");
        }catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(-1);
        }
        return response;

    }

    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public JsonResponse del(String id){
        JsonResponse response = new JsonResponse();
        try {
            userService.deleteById(id);
            response.setMsg("成功");
        }catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(-1);
        }
        return response;
    }
}