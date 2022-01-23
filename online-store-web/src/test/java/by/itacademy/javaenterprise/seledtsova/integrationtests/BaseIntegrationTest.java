package by.itacademy.javaenterprise.seledtsova.integrationtests;

import by.itacademy.javaenterprise.seledtsova.Application;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    static DataSource dataSource;
    static PostgreSQLContainer postgreSQLContainer;

    @BeforeAll
    public static void beforeClass() throws Exception {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:11")
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);
        postgreSQLContainer.start();
        dataSource = new DriverManagerDataSource(postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword());
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }

    @DynamicPropertySource
    @Primary
    public static void setDataSourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("test.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("test.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("test.datasource.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }
}
