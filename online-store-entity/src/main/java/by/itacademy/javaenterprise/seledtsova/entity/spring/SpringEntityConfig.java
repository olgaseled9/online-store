package by.itacademy.javaenterprise.seledtsova.entity.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EntityScan(basePackages = "by.itacademy.javaenterprise.seledtsova.entity")
public class SpringEntityConfig {

}
