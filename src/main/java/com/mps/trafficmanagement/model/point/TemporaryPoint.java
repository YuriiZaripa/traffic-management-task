package com.mps.trafficmanagement.model.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemporaryPoint {

    private double longitude;
    private double latitude;
    private double altitude;
    private double speed;
    private double course;

}
