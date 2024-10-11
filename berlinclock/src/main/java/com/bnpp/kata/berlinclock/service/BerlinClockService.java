package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.springframework.stereotype.Service;

@Service
public class BerlinClockService {

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        String secondsLamp = getSecondsLamp(time);
        String hourLamp = getHoursLamp(time);

        return BerlinClockResponse.builder()
                .detailedBerlinTime(DetailedBerlinTime.builder().secondsLamp(secondsLamp).topFiveHourLamps(hourLamp).build())
                .build();
    }

    private static String getSecondsLamp(TimeComponent time) {
        return (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";
    }

    private static String getHoursLamp(TimeComponent time) {

        String hourLamp;
        if (Integer.parseInt(time.getHours()) >= 5 && Integer.parseInt(time.getHours()) <= 9) {
            hourLamp = "ROOO";
        } else {
            hourLamp = "OOOO";
        }
        return hourLamp;
    }
}
