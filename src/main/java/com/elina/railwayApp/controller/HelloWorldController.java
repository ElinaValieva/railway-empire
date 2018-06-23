package com.elina.railwayApp.controller;

import com.elina.railwayApp.model.User;
import com.elina.railwayApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    @Autowired
    private UserService userService;
/*
откуда  мы получаем model
она спрингом врехачивается сюда
причем возвращается верная вьюшка
но только обрабатывается она как html простой
 */

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(Model model) {
        User user = new User();
//        user.setId(8);
        user.setFirstName("aa");
        user.setLastName("aaa");
        user.setPassword("aa");
        user.setLogin("pass");
        userService.addUser(user);
        Tmp tmp = new Tmp();
        tmp.setTmp("aaaaaaaaaa");
        model.addAttribute("greeting", tmp);
//        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "welcome";
    }

    @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "welcome";
    }
    @RequestMapping(value = "/home")
    public ModelMap home(){
        ModelMap modelMap = new ModelMap("home");
        modelMap.addAttribute("param", "hi beach");
        return modelMap;
    }
}
