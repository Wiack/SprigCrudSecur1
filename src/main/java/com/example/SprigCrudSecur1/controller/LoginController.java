package com.example.SprigCrudSecur1.controller;

import com.example.SprigCrudSecur1.model.User;
import com.example.SprigCrudSecur1.service.RoleService;
import com.example.SprigCrudSecur1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
//@RequestMapping("/login")
public class LoginController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/login";
        }
        user.setRoles(Collections.singleton(roleService.findByName("ROLE_USER")));
        userService.addUser(user);
        return "redirect:/login";
    }
}
