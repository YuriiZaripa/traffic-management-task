package com.mps.trafficmanagement.model;

import com.mps.trafficmanagement.model.point.TemporaryPoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@RequiredArgsConstructor
@Document
public class Airplane {

    @Id
    private String id;
    private final AirplaneCharacteristics characteristics;
    private TemporaryPoint position;
    private List<Flight> flights = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void printFlightInfos() {
        int flightTime = flights.stream()
                .mapToInt(flight -> flight.getPassedPoints().size())
                .sum();

        log.info("Flights: " + flights.size() + ", " + "Total flight time: " + flightTime);
    }
}
