package com.baikati.exception.handling.controller;

import com.baikati.exception.handling.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @GetMapping
    public List<String> getUserAddress(@RequestParam Long id){
        throw new UserNotFoundException(id);
    }

}
