package org.example.projectbcms.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbcms.service.serviceInterface.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    //dependency injection
    private final UserService userService;

    //api tao user
    //get - lay data
    //post - tao data
    //put - update data
    //delete - xoa data
//    @PostMapping("")



}
