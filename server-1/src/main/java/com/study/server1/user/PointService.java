package com.study.server1.user;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class PointService {

    @CircuitBreaker(name = "points", fallbackMethod = "helloFallback")
    public UserDto.Point getPoint(String id) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/server-2/user/" + id + "/point").build().toUri();
        UserDto.Point point = restTemplate.getForObject(uri, UserDto.Point.class);
        log.info("id = {}, point = {}", id, point);
        return point;
    }

    private UserDto.Point helloFallback(String id, Throwable throwable) {
        log.error("hello Fallback error : id = {}, throwable = {}", id, throwable.getMessage());
        return null;
    }

}
