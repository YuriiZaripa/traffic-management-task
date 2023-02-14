package com.mps.trafficmanagement.service;

import com.mps.trafficmanagement.model.AirplaneCharacteristics;
import com.mps.trafficmanagement.model.point.TemporaryPoint;
import com.mps.trafficmanagement.model.point.WayPoint;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class FlightCalculationService {

    private static final double MAX_SPEED_COEFFICIENT = 0.8;
    private static final double SQUARE = 2.0;

    public static List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics, List<WayPoint> wayPoints) {
        List<TemporaryPoint> temporaryPoints = new ArrayList<>();
        double speed = MAX_SPEED_COEFFICIENT * characteristics.getMaxSpeed();
        if (!wayPoints.isEmpty()) {
            WayPoint wayPoint = wayPoints.get(0);
            temporaryPoints.add(new TemporaryPoint(
                    wayPoint.getLongitude(),
                    wayPoint.getLatitude(),
                    wayPoint.getAltitude(),
                    wayPoint.getSpeed(),
                    wayPoint.getCourse()
            ));
        }

        for (int i = 1; i < wayPoints.size(); i++) {
            temporaryPoints.addAll(subroute(wayPoints.get(i - 1), wayPoints.get(i), speed));
        }

        return temporaryPoints;
    }

    private static List<TemporaryPoint> subroute(WayPoint start, WayPoint finish, double speed) {
        final int wayParts = wayParts(start, finish, speed);
        final double longitudeWay = finish.getLongitude() - start.getLongitude();
        final double latitudeWay = finish.getLatitude() - start.getLatitude();
        List<TemporaryPoint> temporaryPoints = new ArrayList<>();

        for (double pointNumber = 1; pointNumber <= wayParts; pointNumber++) {
            temporaryPoints.add(new TemporaryPoint(
                            start.getLongitude() + longitudeWay * pointNumber / (double) wayParts,
                            start.getLatitude() + latitudeWay * pointNumber / (double) wayParts,
                            start.getAltitude(),
                            start.getSpeed(),
                            start.getCourse()
                    )
            );
        }

        TemporaryPoint lastTemporaryPoint = temporaryPoints.get(temporaryPoints.size() - 1);
        if (isNotFinishCoordinates(lastTemporaryPoint, finish))
            temporaryPoints.add(new TemporaryPoint(
                    finish.getLongitude(),
                    finish.getLatitude(),
                    finish.getAltitude(),
                    finish.getSpeed(),
                    finish.getCourse()
            ));

        return temporaryPoints;
    }

    private static boolean isNotFinishCoordinates(TemporaryPoint currentPoint, WayPoint finish) {
        return currentPoint.getLongitude() != finish.getLongitude() || currentPoint.getLatitude() != finish.getLatitude();
    }

    private static int wayParts(WayPoint start, WayPoint finish, double speed) {
        double startLongitude = start.getLongitude();
        double finishLongitude = finish.getLongitude();
        double startLatitude = start.getLatitude();
        double finishLatitude = finish.getLatitude();

        double distance = sqrt(
                pow(finishLongitude - startLongitude, SQUARE) + pow(finishLatitude - startLatitude, SQUARE)
        );

        return (int) (distance / (speed * MAX_SPEED_COEFFICIENT));
    }

}
