package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.springframework.stereotype.Service;

@Service
public class BerlinClockService {

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        String hourLamp ;

        String secondsLamp = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";
        if (Integer.parseInt(time.getHours()) >= 5 && Integer.parseInt(time.getHours()) <= 9) {
            hourLamp = "ROOO";
        } else {
            hourLamp = "OOOO";
        }

        return BerlinClockResponse.builder()
                .detailedBerlinTime(DetailedBerlinTime.builder().secondsLamp(secondsLamp).topFiveHourLamps(hourLamp).build())
                .build();
    }
}
