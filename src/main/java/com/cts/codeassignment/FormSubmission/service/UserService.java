package com.cts.codeassignment.FormSubmission.service;

import com.cts.codeassignment.FormSubmission.beans.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    List<User> getUsers();

    User updateUser(User user);

    boolean deleteUserById(int id);
}
