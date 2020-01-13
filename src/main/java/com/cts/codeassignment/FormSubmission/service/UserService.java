package com.cts.codeassignment.FormSubmission.service;

import com.cts.codeassignment.FormSubmission.beans.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    //boolean saveUser(int id, User user);

    List<User> getUsers();

    //User updateUser(User user);

    User updateUser(User updateUser);

    boolean deleteUserById(int id);
}
