package com.cts.codeassignment.formsubmission.service;

import com.cts.codeassignment.formsubmission.beans.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);
    List<User> getUsers();
    User updateUser(User updateUser);
    boolean deleteUserById(int id);
}
