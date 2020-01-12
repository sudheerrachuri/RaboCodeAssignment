package com.cts.codeassignment.FormSubmission.controller;

import com.cts.codeassignment.FormSubmission.beans.User;
import com.cts.codeassignment.FormSubmission.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("{ \" message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/listofusers")
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userService.getUsers();
        ResponseEntity<?> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping(path = "/updateuser/{id}")
    public ResponseEntity<?> updateUserById(@RequestBody User user){

        ResponseEntity<?> responseEntity;
       // try {
            final User fetchedUser = userService.updateUser(user);
            responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
//        }
        return responseEntity;

    }

    @DeleteMapping(path = "/removeuser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") final int id) {
        ResponseEntity<?> responseEntity;
        //try {
            userService.deleteUserById(id);
            responseEntity = new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
//        } catch (UserNotFoundException e) {
//            responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
//        }
        return responseEntity;
    }


}
