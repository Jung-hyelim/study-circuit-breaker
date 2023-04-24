package com.study.server1.user;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    public UserDto getUserInfo(String id) {
        // user 정보 조회
        String name = getName(id);
        // point 정보 조회
        UserDto.Point point = getPoint(id);

        return UserDto.of(id, name, point);
    }

    private String getName(String id) {
        // DB 조회 "userRepository.findById(id)" 생략
        return "user-name";
    }

    private UserDto.Point getPoint(String id) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/server-2/user/" + id + "/point").build().toUri();
        return restTemplate.getForObject(uri, UserDto.Point.class);
    }

    private RestTemplate getRestTemplate() {
        // 타임아웃 설정
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
        factory.setConnectionRequestTimeout(5000); // 타임아웃 설정 5초
        return new RestTemplate(factory);
    }

}
