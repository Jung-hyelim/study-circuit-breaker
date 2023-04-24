package com.study.server1.user;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PointService pointService;

    public UserDto getUserInfo(String id) {
        // user 정보 조회
        String name = getName(id);
        // point 정보 조회
        UserDto.Point point = pointService.getPoint(id);

        return UserDto.of(id, name, point);
    }

    private String getName(String id) {
        // DB 조회 "userRepository.findById(id)" 생략
        return "user-name";
    }

}
