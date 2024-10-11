package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.store.Lamp;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BerlinClockService {

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        String secondsLamp = getSecondsLamp(time);
        String hourLamp = getHoursLamp(time);
        String oneHourLamp = getOneHourLamp(time);
        String fiveMinuteLamp = getMinuteLamp(time);
        DetailedBerlinTime detailedBerlinTime = createDetailedBerlinTime(secondsLamp,hourLamp,oneHourLamp,fiveMinuteLamp);
        
        return BerlinClockResponse.builder()
                .detailedBerlinTime(detailedBerlinTime)
                .build();
    }

    private String getMinuteLamp(TimeComponent time) {

        int minutes = Integer.parseInt(time.getMinutes());
        StringBuilder lamps = new StringBuilder();

        if (minutes >= 5 && minutes < 10) {
            lamps.append(Lamp.YELLOW.getValue());
        }
        while (lamps.length() < 11) {
            lamps.append(Lamp.OFF.getValue());
        }
        return lamps.toString();
    }

    private DetailedBerlinTime createDetailedBerlinTime(String secondsLamp, String hourLamp, String oneHourLamp, String fiveMinuteLamp) {

        return DetailedBerlinTime.builder()
                .secondsLamp(secondsLamp)
                .topFiveHourLamps(hourLamp)
                .bottomOneHourLamps(oneHourLamp)
                .topFiveMinuteLamps(fiveMinuteLamp)
                .build();
    }

    private String getOneHourLamp(TimeComponent time) {

        int hours = Integer.parseInt(time.getHours());
        return IntStream.range(0, 4).mapToObj(lampIndex -> (lampIndex < hours % 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());
    }

    private static String getSecondsLamp(TimeComponent time) {
        return (Integer.parseInt(time.getSeconds()) % 2 == 0) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
    }

    private static String getHoursLamp(TimeComponent time) {

        int hours = Integer.parseInt(time.getHours());
        return IntStream.range(0, 4).mapToObj(lampIndex -> (lampIndex < hours / 5) ? Lamp.RED.getValue() : Lamp.OFF.getValue()).collect(Collectors.joining());
    }
}
