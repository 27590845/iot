package com.xidian.iot.dataapi.controller;

import com.xidian.iot.dataapi.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
class CommunicateControllerTest {

    @Autowired
    private CommunicateController communicateController;

    @Test
    void getAllCommun() {
        communicateController.getAllCommun();
    }
}