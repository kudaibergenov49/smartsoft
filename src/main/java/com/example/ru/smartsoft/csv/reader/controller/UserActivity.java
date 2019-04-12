package com.example.ru.smartsoft.csv.reader.controller;

import com.example.ru.smartsoft.csv.reader.model.User;
import com.example.ru.smartsoft.csv.reader.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserActivity {
    private final UserRepository userRepository;

    public UserActivity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/usersLastActivities")
    public String activities(Map<String, Object> model) {
        List<User> usersLastActivities = userRepository.usersLastActivities();
        model.put("usersLastActivities", usersLastActivities);
        return "usersLastActivities";
    }
}
