package com.cts.codeassignment.formsubmission.service;

import com.cts.codeassignment.formsubmission.beans.User;
import com.cts.codeassignment.formsubmission.exception.UserListNotFoundException;
import com.cts.codeassignment.formsubmission.exception.UserManagementException;
import com.cts.codeassignment.formsubmission.exception.UserNotFoundException;
import com.cts.codeassignment.formsubmission.repository.UserRepository;
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
        LOGGER.info("start of save method");
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            throw new UserManagementException("Exception occurred while saving the user details");
        }
        userRepository.save(user);
        LOGGER.info("end of save method");
        return true;
    }


    @Override
    public List<User> getUsers() {
        LOGGER.info("start of getUsers method");
        List<User> user = userRepository.findAll();

        if (!user.isEmpty()) {
            return userRepository.findAll();

        }
        LOGGER.info("end of getUsers method");
        throw new UserListNotFoundException("No Users Found");

    }


    @Override
    public User updateUser(User updateUser) {
        LOGGER.info("start of update method");
        LOGGER.debug("updateUser obj -> {}"+ updateUser);
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
            LOGGER.debug("user obj -> {}"+ user);
            return userRepository.save(user);
        }
        catch(Exception e){
       throw new UserManagementException("Exception occurred while updating the customer details", e);
    }}

    @Override
    public boolean deleteUserById(int id) {
        LOGGER.info("start of delete method");
        final User user = userRepository.findById(id).orElse(null);
        LOGGER.debug("user obj -> {} "+ user);
        if (user == null) {
            throw new UserNotFoundException("Could not delete. User not found");
        }
        userRepository.delete(user);
        LOGGER.info("end of delete method");
        return true;
    }

}
