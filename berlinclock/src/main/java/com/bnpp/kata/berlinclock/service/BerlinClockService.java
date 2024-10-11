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
        int hours = Integer.parseInt(time.getHours());

        if (hours >= 20 && hours <= 23)
            hourLamp = "RRRR";
        else if (hours > 15 && hours <= 19)
            hourLamp = "RRRO";
        else if (hours >= 10 && hours <= 14)
            hourLamp = "RROO";
        else if (hours >= 5 && hours <= 9)
            hourLamp = "ROOO";
        else
            hourLamp = "OOOO";

        return hourLamp;
    }
}
