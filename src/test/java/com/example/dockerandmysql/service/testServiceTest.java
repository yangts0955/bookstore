package com.example.dockerandmysql.service;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

@SpringBootTest
class testServiceTest {

    @Spy
    private testService testService = spy(new testService());

    @Test
    public void test(){
        int result = testService.AddTest(1,2);
        assertEquals(3, result);
    }

}