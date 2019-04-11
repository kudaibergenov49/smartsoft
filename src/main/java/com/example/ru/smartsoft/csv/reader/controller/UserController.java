package com.example.ru.smartsoft.csv.reader.controller;

import com.example.ru.smartsoft.csv.reader.model.User;
import com.example.ru.smartsoft.csv.reader.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@GetMapping("/form")
    public String form(Map<String, Object> model) {
        List<FormCount> forms = userRepository.findForms();
        model.put("forms", forms);
        return "form";
    }*/

    @GetMapping("/lastHourUsers")
    public String lastHourUsers(Map<String, Object> model) {
        List<User> lastHourUsers = userRepository.lastHourUsers();
        model.put("lastHourUsers", lastHourUsers);
        return "lastHourUsers";
    }
}