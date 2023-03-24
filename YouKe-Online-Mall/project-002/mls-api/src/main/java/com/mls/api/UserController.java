package com.mls.api;

import com.mls.model.User;
import com.mls.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/users",produces = "application/json=charset=utf-8")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /*可以用来响应json*/
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> findAll(){
        List<User> userList = service.findAll();
        if (userList == null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(Map.of("userList",userList));
        }
    }
}
