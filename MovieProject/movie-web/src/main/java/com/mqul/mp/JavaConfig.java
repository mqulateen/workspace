package com.mqul.mp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ImportResource("classpath:applicationContext.xml")
@EnableWebMvc //equivalent to <mvc:annotation-driven />
public class JavaConfig {
}
