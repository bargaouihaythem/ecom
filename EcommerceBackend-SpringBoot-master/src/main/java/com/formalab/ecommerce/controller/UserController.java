package com.formalab.ecommerce.controller;

import com.formalab.ecommerce.dao.UserRepository;
import com.formalab.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id ) throws Exception{
        return userRepository.findById(id).orElseThrow(()->new Exception("!!!!!"));
    }


    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @DeleteMapping(value="/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable Long id) throws Exception{
        User user = userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
        userRepository.delete(user);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<User> updateStatus (@PathVariable Long id) throws Exception{
        User user = userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
        user.setPassword(user.getPassword());
      //  user.setEtat(!user.getEtat());
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }


}
