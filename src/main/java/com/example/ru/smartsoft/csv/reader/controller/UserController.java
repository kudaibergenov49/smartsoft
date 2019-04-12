package com.example.ru.smartsoft.csv.reader.controller;

import com.example.ru.smartsoft.csv.reader.model.FormCount;
import com.example.ru.smartsoft.csv.reader.model.User;
import com.example.ru.smartsoft.csv.reader.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model) {
        List<String> forms = userRepository.top5Forms();
        List<FormCount> formCountList = new ArrayList<>();
        for (String form : forms) {
            FormCount formCount = new FormCount();
            if (form == null) form = "";
            formCount.setFormid(form);
            Integer count = userRepository.formCount(form);
            formCount.setCount(count);
            formCountList.add(formCount);
        }
        model.put("forms", formCountList);
        return "form";
    }

    @GetMapping("/lastHourActivities")
    public String lastHourUsers(@PageableDefault(size = 10) Pageable pageable, Map<String, Object> model) {
        Page<User> activities = userRepository.lastHourActivities(pageable);
        model.put("lastHourActivities", activities);
        return "lastHourActivities";
    }

    @GetMapping("/usersLastActivities")
    public String activities(@PageableDefault(size = 10) Pageable pageable, Map<String, Object> model) {
        int pageNumber = pageable.getPageNumber();
        Page<User> usersLastActivities = userRepository.usersLastActivities(pageable,pageNumber);
        model.put("usersLastActivities", usersLastActivities);
        return "usersLastActivities";
    }
}