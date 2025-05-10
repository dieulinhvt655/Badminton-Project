package org.example.projectbcms.controllers;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.dtos.LoginRequest;
import org.example.projectbcms.models.User;
import org.example.projectbcms.services.serviceInterfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Controller - SSR - laf controller chi phat trien api
@RestController  // SPA - api
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    //dependency injection
    private final UserService userService;
    //api tao user
    //get - lay data
    //post - gui data
    //put - update data
    //delete - xoa data

    @PostMapping("/dang-ki")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    // dang nhap
    @PostMapping("/dang-nhap")
    public User logIn(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userService.login(username,password);

        if(user == null) {
            throw new RuntimeException();
        }
        return user;
    }

    @PutMapping("/cap-nhat/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userUpdate ) {
        return userService.updateUser(id,userUpdate);
    }

    @DeleteMapping("/xoa-nguoi-dung/{id}")
    public ResponseEntity<?> deletelUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hien-thi-nguoi-dung/{id}")
    public User displayUser (@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/hien-thi-tat-ca-nguoi-dung")
    public List<User> displayAllUser(){
        return userService.getAllUsers();
    }


}
