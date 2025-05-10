package org.example.projectbcms.services.serviceInterfaces;

import org.example.projectbcms.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //CRUD user
    //create
    User createUser(User user);
    //read
    User getUserById(Long id);

    List<User> getAllUsers();
    //update
    User updateUser(Long id, User updatedUser);
    //delete
    void deleteUser(Long id);

    User login(String username, String password);
}
