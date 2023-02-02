package com.remotelabs.hire;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Testcontainers
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(scripts = {"/scripts/V2__InsertCountries.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/scripts/V3__InsertTechnologies.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/scripts/V4__InsertCandidates.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class BaseIntegrationTestsIT {

    @Autowired
    public MockMvc mockMvc;

    @Container
    private static final DbContainer dbContainer = DbContainer.getInstance();
}
