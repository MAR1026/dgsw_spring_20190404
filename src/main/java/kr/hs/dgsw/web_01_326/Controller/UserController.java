package kr.hs.dgsw.web_01_326.Controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userlist")
    public List<User> getAllUsers() {
     return this.userService.listAllUsers();
    }

    @GetMapping("/search/{userid}")
    public User searchUser(@PathVariable Long userid){
        return this.userService.searchUser(userid);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/update/{userid}")
    public User updateUser(@PathVariable Long userid, @RequestBody User user) {
        return this.userService.updateUser(userid, user);
    }

    @DeleteMapping("/delete/{userid}")
    public boolean deleteUser(@PathVariable Long userid)
    {
        return this.userService.deleteUser(userid);
    }

    @PostMapping("/upload/{userid}")
    public AttachmentProtocol uploadProfile(@PathVariable Long userid, @RequestBody AttachmentProtocol srcFile){
        return this.userService.uploadUserImage(userid, srcFile);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return this.userService.login(user);
    }
}
