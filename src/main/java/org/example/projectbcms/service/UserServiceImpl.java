package org.example.projectbcms.service;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.repository.UserRepository;
import org.example.projectbcms.service.serviceInterface.UserService;
import org.example.projectbcms.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//lombok: tu dong viet constructor
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //dependency injection
    private final UserRepository userRepository;

    @Override
    public User createUser(User newUser) {
      return userRepository.save(newUser);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
    //check if user exists
        User userPresent = getUserById(id);
        if (userPresent != null) {
            userPresent.setPassword(updatedUser.getPassword()); //hashing password
            userPresent.setEmail(updatedUser.getEmail());
            userPresent.setFullName(updatedUser.getFullName());
            userPresent.setPhoneNumber(updatedUser.getPhoneNumber());
            userPresent.setRole(updatedUser.getRole());
        }
        assert userPresent != null; //user phai khac null
        return userRepository.save(userPresent);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
