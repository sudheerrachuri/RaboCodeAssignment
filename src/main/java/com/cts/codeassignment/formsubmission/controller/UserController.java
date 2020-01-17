package com.cts.codeassignment.formsubmission.controller;

import com.cts.codeassignment.formsubmission.beans.User;
import com.cts.codeassignment.formsubmission.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/registeruser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        LOGGER.info("start of create user method");
        User userResponse=userService.saveUser(user);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(userResponse,HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/listofusers")
    public ResponseEntity<?> getAllUsers() {
        LOGGER.info("start of get all users method");
        List<User> users = userService.getUsers();
        ResponseEntity<?> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        LOGGER.info("end of get all users method");
        return responseEntity;
    }

    @PutMapping(path = "/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        LOGGER.info("start of update user method");
        ResponseEntity<?> responseEntity;
            final User fetchedUser = userService.updateUser(user);
            responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
        LOGGER.info("end of update user method");
        return responseEntity;
    }

    @DeleteMapping(path = "/removeuser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") final String id) {
        LOGGER.info("start of delete user method");
        ResponseEntity<?> responseEntity;
       int  resultId = Integer.parseInt(id);
            userService.deleteUserById(resultId);
            responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        LOGGER.info("end of delete user method");
        return responseEntity;
    }

}
