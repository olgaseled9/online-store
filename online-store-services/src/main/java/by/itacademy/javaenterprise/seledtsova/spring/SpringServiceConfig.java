package by.itacademy.javaenterprise.seledtsova.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"by.itacademy.javaenterprise.seledtsova.service",
        "by.itacademy.javaenterprise.seledtsova.dto",
        "by.itacademy.javaenterprise.seledtsova.convectors"})
public class SpringServiceConfig {

}
