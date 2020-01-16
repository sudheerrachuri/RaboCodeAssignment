package com.cts.codeassignment.FormSubmission.service;

import com.cts.codeassignment.FormSubmission.beans.User;
import com.cts.codeassignment.FormSubmission.exception.UserListNotFoundException;
import com.cts.codeassignment.FormSubmission.exception.UserManagementException;
import com.cts.codeassignment.FormSubmission.exception.UserNotFoundException;
import com.cts.codeassignment.FormSubmission.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean saveUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            throw new UserManagementException("Exception occurred while saving the user details");
        }
        userRepository.save(user);
        return true;
    }


    @Override
    public List<User> getUsers() {
        List<User> user = userRepository.findAll();

        if (!user.isEmpty()) {
            return userRepository.findAll();
        }

        throw new UserListNotFoundException("No Users Found");

    }


    @Override
    public User updateUser(User updateUser) {
        LOGGER.info("start of uppdate method");
        LOGGER.debug("updateUser obj -> {}", updateUser);
        final User user = userRepository.findById(updateUser.getId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Could not update. User not found");
        }
        try {
            user.setName(updateUser.getName());
            user.setMobileNumber(updateUser.getMobileNumber());
            user.setEmail(updateUser.getEmail());
            user.setLanguage(updateUser.getLanguage());
            user.setGender(updateUser.getGender());
            LOGGER.debug("user obj -> {}", user);
            return userRepository.save(user);
        }
        catch(Exception e){
       throw new UserManagementException("Exception occurred while updating the customer details", e);
    }}

    @Override
    public boolean deleteUserById(int id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Could not delete. User not found");
        }
        userRepository.delete(user);
        return true;
    }

}
