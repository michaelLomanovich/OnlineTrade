package com.lomrom.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@AutoConfigureMockMvc
public class ASpringIntegrationTest {

    private static final String DB_NAME = "shop";
    private static final String DB_USER_NAME = "shop";
    private static final String DB_PWD = "password";

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper mapper;

    @Container
    public static GenericContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres"))
            .withDatabaseName(DB_NAME)
            .withPassword(DB_PWD)
            .withUsername(DB_USER_NAME)
            .withInitScript("db/init_postgres.sql")
            .withExposedPorts(5432)
            .withStartupTimeout(Duration.ofMinutes(10));


    @BeforeAll
    public static void setUp() {
        String pgAddress = postgres.getHost();
        Integer pgPort = postgres.getFirstMappedPort();
        System.setProperty("DB_URL","jdbc:postgresql://"+pgAddress+":"+pgPort+"/"+DB_NAME);
        System.setProperty("DB_USER_NAME",DB_USER_NAME);
        System.setProperty("DB_USER_PWD",DB_PWD);
    }
}
