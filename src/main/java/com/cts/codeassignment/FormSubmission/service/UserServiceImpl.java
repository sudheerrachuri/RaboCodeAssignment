package com.cts.codeassignment.FormSubmission.service;

import com.cts.codeassignment.FormSubmission.beans.User;
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
//        if (existingUser.isPresent()) {
//            //throw new UserAlreadyExistsException("User with id already exists");
//        }
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User updateUser) {
        final User user = userRepository.findById(updateUser.getId()).orElse(null);
//        if (user == null) {
//            throw new UserNotFoundException("Could not update. User not found");
//        }
        //user.setComments(updateUser.getComments());
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUserById(int id) {
        final User user = userRepository.findById(id).orElse(null);
//        if (user == null) {
//            throw new UserNotFoundException("Could not delete. User not found");
//        }
        userRepository.delete(user);
        return true;
    }

}
