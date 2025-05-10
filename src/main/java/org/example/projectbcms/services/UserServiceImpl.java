package org.example.projectbcms.services;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.repositories.UserRepository;
import org.example.projectbcms.services.serviceInterfaces.UserService;
import org.example.projectbcms.models.User;
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
        // tìm kiếm user qua id
        Optional<User> user = userRepository.findById(id);
        // nếu != null -> trả về user, khum thì orElse trả về 1 String ....
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
        //
        userPresent.setEmail(updatedUser.getEmail());
        userPresent.setPassword(updatedUser.getPassword());
        userPresent.setPhoneNumber(updatedUser.getPhoneNumber());
        userPresent.setRole(updatedUser.getRole());

        return userRepository.save(userPresent);
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
