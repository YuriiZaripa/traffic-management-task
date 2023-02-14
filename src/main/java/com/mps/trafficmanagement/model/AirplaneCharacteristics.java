package com.mps.trafficmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneCharacteristics {

    private double maxSpeed;
    private double acceleration;
    private double altitudeSpeed;
    private double courseSpeed;

}
