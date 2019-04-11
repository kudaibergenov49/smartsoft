package com.example.ru.smartsoft.csv.reader.controller;

import com.example.ru.smartsoft.csv.reader.model.FormCount;
import com.example.ru.smartsoft.csv.reader.model.User;
import com.example.ru.smartsoft.csv.reader.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/form")
    public String form(Map<String, Object> model) {
        List<String> forms = userRepository.findTop5Forms();
        List<FormCount> formCountList = new ArrayList<>();
        for (String form : forms) {
            FormCount formCount = new FormCount();
            if (form == null) form = "";
            formCount.setFormid(form);
            Integer count = userRepository.findFormCount(form);
            formCount.setCount(count);
            formCountList.add(formCount);
        }
        model.put("forms", formCountList);
        return "form";
    }

    @GetMapping("/lastHourUsers")
    public String lastHourUsers(@RequestParam("pageSize") Optional<Integer> pageSize,
                                @RequestParam("page") Optional<Integer> page, Map<String, Object> model) {
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<User> users = userRepository.lastHourUsers(PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        model.put("users", users);
        model.put("selectedPageSize", evalPageSize);
        model.put("pageSizes", PAGE_SIZES);
        model.put("pager", pager)
        return "lastHourUsers";
    }
}