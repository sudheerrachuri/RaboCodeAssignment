package com.cts.codeassignment.FormSubmission.controller;

import com.cts.codeassignment.FormSubmission.beans.User;
import com.cts.codeassignment.FormSubmission.service.UserService;
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

    @PostMapping("/register")
    public User createUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/listofusers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getUsers();
        ResponseEntity<?> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping(path = "/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        ResponseEntity<?> responseEntity;
            final User fetchedUser = userService.updateUser(user);
            responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(path = "/removeuser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") final String id) {
        ResponseEntity<?> responseEntity;
       int  resultId = Integer.parseInt(id);
            userService.deleteUserById(resultId);
            responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        return responseEntity;
    }

}
