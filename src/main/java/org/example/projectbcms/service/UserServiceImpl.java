package org.example.projectbcms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
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
        // Kiểm tra xem user có tồn tại không
        User userPresent = getUserById(id);

        if (userPresent != null) {
            userPresent.setPassword(Optional.ofNullable(updatedUser.getPassword())
                    .orElse(userPresent.getPassword()));

            userPresent.setEmail(Optional.ofNullable(updatedUser.getEmail())
                    .orElse(userPresent.getEmail()));

            userPresent.setFullName(Optional.ofNullable(updatedUser.getFullName())
                    .orElse(userPresent.getFullName()));

            userPresent.setPhoneNumber(Optional.ofNullable(updatedUser.getPhoneNumber())
                    .orElse(userPresent.getPhoneNumber()));

            userPresent.setRole(Optional.ofNullable(updatedUser.getRole())
                    .orElse(userPresent.getRole()));

            return userRepository.save(userPresent);
        }

        return null; // Trả về null nếu user không tồn tại
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        System.out.println("xoa thanh cong");
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
