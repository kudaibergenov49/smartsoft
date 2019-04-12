package com.example.ru.smartsoft.csv.reader.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
    @Autowired
    private HelloController helloController;

    @Test
    public void hello() {
        Assert.assertEquals(helloController.hello(), "hello");
    }
}