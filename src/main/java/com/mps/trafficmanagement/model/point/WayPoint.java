package com.mps.trafficmanagement.model.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WayPoint {

    private double longitude;
    private double latitude;
    private double altitude = 100;
    private double speed = 60;
    private double course = 360;

    public WayPoint(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
