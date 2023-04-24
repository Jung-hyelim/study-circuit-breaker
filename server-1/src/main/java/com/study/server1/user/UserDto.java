package com.study.server1.user;

import lombok.Getter;
import lombok.ToString;

@Getter
public class UserDto {

    private final String id;
    private final String name;
    private final Point point;

    @ToString
    @Getter
    public static class Point {
        private Long remainPoint;

        public Point() {
        }

        private Point(Long remainPoint) {
            this.remainPoint = remainPoint;
        }

        public static Point of(Long remainPoint) {
            return new Point(remainPoint);
        }
    }

    private UserDto(String id, String name, Point point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public static UserDto of(String id, String name, Point point) {
        return new UserDto(id, name, point);
    }
}
