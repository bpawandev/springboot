package com.baikati.exception.handling.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(@RequestParam(required = false) String code){
        if("400".equals(code)){
            throw new BadRequestException("Bad code");
        }
        if("403".equals(code)){
            throw new AccessDeniedRequestException("you are not authorized to access this page");
        }
        return "index";
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.FORBIDDEN)
class AccessDeniedRequestException extends RuntimeException{
    public AccessDeniedRequestException(String message) {
        super(message);
    }
}