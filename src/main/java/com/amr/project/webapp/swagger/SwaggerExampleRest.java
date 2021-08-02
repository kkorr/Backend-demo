package com.amr.project.webapp.swagger;

import com.amr.project.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/swaggertest")
public class SwaggerExampleRest {
    List<User> users = new ArrayList<>();

    {
        users.add(new User(1L, "testuser@test.com", "testuser"));
        users.add(new User(2L, "testuser2@test.com", "testuser2"));
        users.add(new User(3L, "testuser3@test.com", "testuser3"));
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PatchMapping("/editUser/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody User user) {
        users = users.stream().filter(el -> !el.getId().equals(id)).collect(Collectors.toList());
        users.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        users.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        users = users.stream().filter(el -> !el.getId().equals(id)).collect(Collectors.toList());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
