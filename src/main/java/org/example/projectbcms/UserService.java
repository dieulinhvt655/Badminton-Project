package org.example.projectbcms;

import java.util.List;

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
}
