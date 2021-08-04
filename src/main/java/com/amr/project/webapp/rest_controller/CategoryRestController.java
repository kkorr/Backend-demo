package com.amr.project.webapp.rest_controller;

import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

/*    @PutMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return (user1 != null)
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        Long id = userService.addUser(user);
        return (id != 0)
                ? new ResponseEntity<>(id, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }*/

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        categoryService.deleteByKeyCascadeIgnore(id);
    }
}
