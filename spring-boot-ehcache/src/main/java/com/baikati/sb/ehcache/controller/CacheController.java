package com.baikati.sb.ehcache.controller;

import com.baikati.sb.ehcache.service.CacheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/get/{number}")
    public ResponseEntity<Long> findFactorial(@PathVariable long number){
        Long factorial = cacheService.getFactorial(number);
        return  new ResponseEntity<>(factorial, HttpStatus.OK);
    }

    @GetMapping("/put/{number}")
    public ResponseEntity<Long> add(@PathVariable long number){
        Long fact = cacheService.update(number);
        return new ResponseEntity<>(fact,HttpStatus.OK);
    }

    @GetMapping("/evict")
    public ResponseEntity<String> evict(){
        cacheService.evictData();
        return new ResponseEntity<>("cache cleaned",HttpStatus.OK);
    }
}
