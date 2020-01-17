package com.cts.codeassignment.formsubmission.service;

import com.cts.codeassignment.formsubmission.FormSubmissionApp;
import com.cts.codeassignment.formsubmission.beans.User;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = FormSubmissionApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

    public static long getId() {
        return id;
    }

    public static void setId(int id) {
        UserServiceImplTest.id = id;
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    static int id;

    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Before
    public void init(){
        System.out.println("init done");
    }



    @Test
    @Order(1)
    public void testGetAllUsers() {
        LOGGER.info("start of get all users test");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String getResponse = restTemplate.getForObject(getUrl() + "/customerManagement/user/listofusers", String.class);
        LOGGER.debug("getResponse===> {} "+getResponse);
        LOGGER.info("end of get all users test ");
        assertNotNull(getResponse);
    }


    @Test
    @Order(2)
    public void testCreateUser() {
        LOGGER.info("start of create user test");
        User user = new User();
        user.setName("admin");
        user.setMobileNumber("8907654321");
        user.setLanguage("English");
        user.setEmail("admin@gmail.com");
        user.setGender("male");
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getUrl() + "/customerManagement/user/register", user, User.class);
        user=postResponse.getBody();
        this.setId(user.getId());
        LOGGER.debug("id===>{}"+this.getId());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        LOGGER.info("end of create user test");
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        LOGGER.info("start of update user test");
        User user = new User();
        user.setName("user");
        user.setMobileNumber("7890123456");
        user.setLanguage("Dutch");
        user.setEmail("user@gmail.com");
        user.setGender("female");
        restTemplate.put(getUrl() + "/customerManagement/user/updateuser/" + this.getId(), user);
        LOGGER.info("end of update user test");
    }

    @Test
    @Order(4)
    public void testDeleteUser() {
        LOGGER.info("start of delte user test");
        try {
            LOGGER.debug("getId2==>{}"+this.getId());
            restTemplate.delete(getUrl() + "/customerManagement/user/removeuser/"+ this.getId());
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
        LOGGER.info("end of delete user test");
    }
}
