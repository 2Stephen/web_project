package com.whut.backproject.controller;

import com.whut.backproject.POJO.ChangePwdData;
import com.whut.backproject.POJO.RegisterData;
import com.whut.backproject.POJO.UserData;
import com.whut.backproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> isVerified(@RequestBody UserData data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String authToken = "success";
        boolean isAuthenticated = userService.authenticate(username, password);
        if (isAuthenticated) return ResponseEntity.ok(authToken);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterData data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String email = data.getEmail();
        String birthday = data.getBirthday();
        System.out.println(username + " " + password);
        boolean isRegistered = userService.register(username, password, email, birthday);
        if (isRegistered) return ResponseEntity.ok("success");
        else return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    @PostMapping("changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePwdData data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String newPassword = data.getNewPassword();
        System.out.println(data);
        boolean isChanged = userService.changePassword(username, password, newPassword);
        if (isChanged) return ResponseEntity.ok("success");
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
