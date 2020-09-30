package com.telexiom.ngschulung.controller;

import com.telexiom.ngschulung.documents.User;
import com.telexiom.ngschulung.models.UserRequest;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telexiom.ngschulung.repositories.UserRepository;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "https://ngtelexiom.web.app", "https://ngtelexiom.firebaseapp.com"}, maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers(@RequestHeader Map<String, String> headers) {
        return userRepository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity getUserByEmail(
            @PathVariable(name = "email") String email,
            @RequestHeader Map<String, String> headers
    ) {
        final User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + email);
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(
            @RequestBody UserRequest req,
            @RequestHeader Map<String, String> headers
    ) throws IllegalArgumentException {
        try {
            if (!req.isValidRequest()) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }


        User user = new User(
                req.email,
                req.language,
                req.firstname,
                req.lastname,
                req.birthdate,
                req.contactphone
        );
        user = userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{email}/remove")
    public ResponseEntity removeUserByEmail(
            @PathVariable(name = "email") String email,
            @RequestHeader Map<String, String> headers
    ) {
        final User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + email);
        }

        userRepository.delete(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/{email}/update")
    public ResponseEntity updateUserByEmail(
            @PathVariable(name = "email") String email,
            @RequestBody UserRequest req,
            @RequestHeader Map<String, String> headers
    ) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found: " + email);
        }

        user = user.updateUser(
                user.id,
                !req.email.isEmpty() ? req.email : user.email,
                !req.language.isEmpty() ? req.language : user.language,
                !req.firstname.isEmpty() ? req.firstname : user.firstname,
                !req.lastname.isEmpty() ? req.lastname : user.lastname,
                req.birthdate != null ? req.birthdate : user.birthdate,
                !req.contactphone.isEmpty() ? req.contactphone : user.contactphone
        );
        user = userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}