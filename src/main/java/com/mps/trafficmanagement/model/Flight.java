package com.mps.trafficmanagement.model;

import com.mps.trafficmanagement.model.point.TemporaryPoint;
import com.mps.trafficmanagement.model.point.WayPoint;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Flight {

    private Long id;
    private List<WayPoint> wayPoints;
    private List<TemporaryPoint> passedPoints = new ArrayList<>();

    public Flight(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public void addPassedPoint(TemporaryPoint point) {
        passedPoints.add(point);
    }

}
