package com.example.server2.point;

import lombok.Getter;
import lombok.ToString;

@Getter
public class PointDto {

    private final Long remainPoint;

    private PointDto (Long remainPoint) {
        this.remainPoint = remainPoint;
    }

    public static PointDto of(Long remainPoint) {
        return new PointDto(remainPoint);
    }
}
