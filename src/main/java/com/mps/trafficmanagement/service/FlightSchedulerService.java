package com.mps.trafficmanagement.service;

import com.mps.trafficmanagement.model.FlightTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component
public class FlightSchedulerService {

    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(3);

    public void startFlight(FlightTask task) {
        executorService.execute(task);
    }

}
