package com.amr.project.webapp.rest_controller;

import com.amr.project.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/com.arm.project.webapp.rest_controller/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "petr")
public class CartRestControllerTest extends AbstractIntegrationTest {

    @Test
    void addItemToCart() {

    }
}
