package com.usermanagement.mycompany.controller;

import com.usermanagement.mycompany.controller.exceptions.UserNotFoundException;
import com.usermanagement.mycompany.entity.User;
import com.usermanagement.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showAllUsers(Model model){
        List<User> usersList = userService.listAllUsers();

        model.addAttribute("usersList", usersList);
        return "users";
    }

    @GetMapping("/users/new")
    public String showAddNewUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "add_user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "User has been added successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer userId, Model model, RedirectAttributes redirectAttributes){
        try{
            User user = userService.getUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit UserID: "+ userId);
            return "add_user_form";

        }
        catch(UserNotFoundException userNotFoundException){
            userNotFoundException.printStackTrace();
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer userId, RedirectAttributes redirectAttributes){
        userService.deleteById(userId);
        redirectAttributes.addFlashAttribute("message", "User with ID: " +userId + " has been deleted.");
        return "redirect:/users";
    }



}
