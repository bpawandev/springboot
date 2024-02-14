package com.baikati.exception.handling.service;

import com.baikati.exception.handling.entity.User;
import com.baikati.exception.handling.exception.UserNotFoundException;
import com.baikati.exception.handling.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
}
