package com.study.server1.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/server-1/user/{id}")
    public UserDto userInfo(@PathVariable String id) {
        log.info("사용자 정보 조회 user id = {}", id);
        UserDto user = userService.getUserInfo(id);
        return user;
    }
}
