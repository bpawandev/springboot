package com.baikati.sb.ehcache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class CacheService {

    @Cacheable("numbers")
    public Long getFactorial(long number) {
        return calculateFactorial(number);
    }

    private Long calculateFactorial(long number) {
        System.out.println("Calculating...");
        return LongStream.rangeClosed(1, number).reduce(1, (x, y) -> x * y);
    }

    @CachePut(value = "numbers", condition = "#id>10")
    public Long update(Long number) {
        return calculateFactorial(number);
    }

    @CacheEvict(value = "numbers", condition = "#id>15")
    public void evictData() {
        System.out.println("cache evicted");
    }
}
