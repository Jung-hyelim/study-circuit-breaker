package com.example.server2.point;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PointController {

    @GetMapping("/server-2/user/{id}/point")
    public PointDto userPoint(@PathVariable String id) {
        log.info("user id : {} 의 포인트 조회", id);
        if ("jhl".equals(id)) {
            throw new RuntimeException("강제 에러!! ");
        }
        return PointDto.of(1000L);
    }
}
