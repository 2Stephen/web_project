package com.whut.backproject.controller;

import com.whut.backproject.POJO.ChangePwdData;
import com.whut.backproject.POJO.RegisterData;
import com.whut.backproject.POJO.UserData;
import com.whut.backproject.POJO.UserInfo;
import com.whut.backproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;
    private String avatarUrl = "";
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
        boolean isRegistered = userService.register(username, password, email, birthday, avatarUrl);
        if (isRegistered) return ResponseEntity.ok("success");
        else return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePwdData data) {
        String username = data.getUsername();
        String password = data.getPassword();
        String newPassword = data.getNewPassword();
        boolean isChanged = userService.changePassword(username, password, newPassword);
        if (isChanged) return ResponseEntity.ok("success");
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @PostMapping("/userinfo")
    public ResponseEntity<String> getUserInfo(@RequestBody UserData data) {
        UserInfo user = userService.getUserByUsername(data.getUsername());
        if (user != null) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/uploadavatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam MultipartFile file) {
        String avatar = file.getOriginalFilename();
        if (avatar != null && !(avatar.endsWith(".jpg") || avatar.endsWith(".png") || avatar.endsWith(".jpeg"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type");
        }
        // 保存上传的头像文件到本地服务器
        String uploadPath = "/Users/hanlelin/Desktop/documents/web_exp/exp-02/back-project/uploads/"; // 上传文件的存储路径
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        try {
            File dest = new File(uploadPath + fileName);
            //将图片路径存在数据库中
            avatarUrl = "http://localhost:8081/uploads/" + fileName;
            file.transferTo(dest);
            // 返回头像文件路径给前端
            return ResponseEntity.ok("http://localhost:8081/uploads/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        }
    }
}
