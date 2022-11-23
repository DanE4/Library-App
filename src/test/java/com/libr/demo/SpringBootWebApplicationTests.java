package com.libr.demo;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class SpringBootWebApplicationTests {
    @Test
    public void contextLoads() {
    }
}