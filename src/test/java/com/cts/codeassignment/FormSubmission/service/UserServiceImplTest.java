package com.cts.codeassignment.FormSubmission.service;

import com.cts.codeassignment.FormSubmission.App;
import com.cts.codeassignment.FormSubmission.beans.User;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {

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
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String getResponse = restTemplate.getForObject(getUrl() + "/customerManagement/user/listofusers", String.class);
        System.out.println("getResponse===>"+getResponse);
        assertNotNull(getResponse);
    }


    @Test
    @Order(2)
    public void testCreateUser() {

        User user = new User();
        user.setName("admin");
        user.setMobileNumber("8907654321");
        user.setLanguage("English");
        user.setEmail("admin@gmail.com");
        user.setGender("male");
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getUrl() + "/customerManagement/user/register", user, User.class);
        user=postResponse.getBody();
        this.setId(user.getId());
        System.out.println("id===>"+this.getId());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @Order(3)
    public void testUpdateUser() {

        User user = new User();
        user.setName("user");
        user.setMobileNumber("7890123456");
        user.setLanguage("Dutch");
        user.setEmail("user@gmail.com");
        user.setGender("female");
        restTemplate.put(getUrl() + "/customerManagement/user/updateuser/" + this.getId(), user);
    }

    @Test
    @Order(4)
    public void testDeleteUser() {
        try {
            System.out.println("getId2==>"+this.getId());
            restTemplate.delete(getUrl() + "/customerManagement/user/removeuser/"+ this.getId());
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }

    }
}
