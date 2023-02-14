package com.mps.trafficmanagement;

import com.mps.trafficmanagement.model.Airplane;
import com.mps.trafficmanagement.model.AirplaneCharacteristics;
import com.mps.trafficmanagement.model.Flight;
import com.mps.trafficmanagement.model.FlightTask;
import com.mps.trafficmanagement.model.point.WayPoint;
import com.mps.trafficmanagement.persistence.AirplaneRepository;
import com.mps.trafficmanagement.service.FlightSchedulerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TrafficManagementApplication {

    public static void main(String[] args) throws InterruptedException {
        var context = SpringApplication.run(TrafficManagementApplication.class, args);
        var repository = context.getBean(AirplaneRepository.class);

        Airplane airplane1 = new Airplane(new AirplaneCharacteristics(
                25.0,
                20.0,
                20.0,
                25.0));

        Airplane airplane2 = new Airplane(new AirplaneCharacteristics(
                20.0,
                16.0,
                16.0,
                20.0));

        Airplane airplane3 = new Airplane(new AirplaneCharacteristics(
                15.0,
                17.0,
                17.0,
                21.0));

        List<WayPoint> wayPoints = List.of(
                new WayPoint(0, 0),
                new WayPoint(100, 0),
                new WayPoint(100, 100),
                new WayPoint(200, 200)
        );

        var flightSchedule = context.getBean(FlightSchedulerService.class);
        flightSchedule.startFlight(new FlightTask(airplane1, new Flight(wayPoints), repository));
        flightSchedule.startFlight(new FlightTask(airplane2, new Flight(wayPoints), repository));
        flightSchedule.startFlight(new FlightTask(airplane3, new Flight(wayPoints), repository));

        flightSchedule.startFlight(new FlightTask(airplane1, new Flight(wayPoints), repository));
        flightSchedule.startFlight(new FlightTask(airplane2, new Flight(wayPoints), repository));
        flightSchedule.startFlight(new FlightTask(airplane3, new Flight(wayPoints), repository));
    }

}
