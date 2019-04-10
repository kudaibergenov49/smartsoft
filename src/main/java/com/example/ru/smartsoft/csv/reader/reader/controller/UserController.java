package com.example.ru.smartsoft.csv.reader.reader.controller;

import com.example.ru.smartsoft.csv.reader.reader.model.User;
import com.example.ru.smartsoft.csv.reader.reader.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "form";
    }
}
