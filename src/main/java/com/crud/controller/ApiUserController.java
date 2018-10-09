package com.crud.controller;

//import org.springframework.web.bind.annotation.RestController;

import com.crud.Exception.UserNotFoundException;
import com.crud.model.Role;
import com.crud.model.User;
import com.crud.service.abstraction.RoleService;
import com.crud.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getAllUsers(@PathVariable(value = "id") long id) {
        User user = userService.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not dound by id = " + id);
        } else {
            return user;
        }
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        for (Role roleInput : user.getRoles()) {
            Role role = roleService.getByName(roleInput.getName());
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User userInput) {
        User user = userService.get(userInput.getId());
        if (user == null) {
            throw new UserNotFoundException("User not dound by id = " + userInput.getId());
        } else {
            user.setName(userInput.getName());
            user.setLogin(userInput.getLogin());
            user.setPassword(userInput.getPassword());
            Set<Role> roles = new HashSet<>();
            for (Role roleInput : userInput.getRoles()) {
                Role role = roleService.getByName(roleInput.getName());
                roles.add(role);
            }
            user.setRoles(roles);

            userService.update(user);
        }
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        User user = userService.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not dound by id = " + id);
        } else {
            userService.delete(user);
        }
    }

}
