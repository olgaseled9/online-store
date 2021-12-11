package by.itacademy.javaenterprise.seledtsova.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Properties;


@Configuration
@ComponentScan(basePackages = {"by.itacademy.javaenterprise.seledtsova.dao"})
@PropertySource(value = {"classpath:db.properties"})
@EnableTransactionManagement
public class SpringPersistenceConfig {

    @Autowired
    Environment environment;

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("db.driver"));
        basicDataSource.setUrl(environment.getProperty("db.url"));
        basicDataSource.setUsername(environment.getProperty("db.user"));
        basicDataSource.setPassword(environment.getProperty("db.password"));
        basicDataSource.setMaxIdle(environment.getProperty("db.maxTotal", Integer.class));
        return basicDataSource;
    }


    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setPackagesToScan("by.itacademy.javaenterprise.seledtsova.entity");

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show.sql"));

        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getNativeEntityManagerFactory());
        return transactionManager;
    }

    @Bean
    TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        transactionTemplate.setTimeout(environment.getProperty("transaction.timeout", Integer.class));
        return transactionTemplate;
    }
}
