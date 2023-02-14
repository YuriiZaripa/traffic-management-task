package com.mps.trafficmanagement.model;

import com.mps.trafficmanagement.model.point.TemporaryPoint;
import com.mps.trafficmanagement.persistence.AirplaneRepository;
import com.mps.trafficmanagement.service.FlightCalculationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class FlightTask implements Runnable {

    private final Airplane airplane;
    private final Flight flight;
    private final AirplaneRepository repository;

    @Override
    public void run() {
        airplane.printFlightInfos();
        List<TemporaryPoint> points = FlightCalculationService.calculateRoute(airplane.getCharacteristics(), flight.getWayPoints());

        airplane.addFlight(flight);
        repository.save(airplane);
        for (TemporaryPoint point : points) {
            flight.addPassedPoint(point);
            airplane.setPosition(point);
            log.debug("Board: " + airplane.getId() + " (" + point.getLongitude() + ", " + point.getLatitude() + ")");

            repository.save(airplane);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
