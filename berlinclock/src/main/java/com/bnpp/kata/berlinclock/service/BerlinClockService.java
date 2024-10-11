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
        String oneMinuteLamp = getOneMinuteLamp(time);
        DetailedBerlinTime detailedBerlinTime = createDetailedBerlinTime(secondsLamp,hourLamp,oneHourLamp,fiveMinuteLamp,oneMinuteLamp);
        
        return BerlinClockResponse.builder()
                .detailedBerlinTime(detailedBerlinTime)
                .build();
    }

    private String getOneMinuteLamp(TimeComponent time) {

        int minutes = Integer.parseInt(time.getMinutes());
        String mintLamps ;

        if (minutes % 5 == 2)
            mintLamps =  "YYOO";
        else if (minutes % 5 == 1)
            mintLamps = "YOOO";
        else
            mintLamps = "OOOO";

        return mintLamps;
    }

    private String getMinuteLamp(TimeComponent time) {

        int minutes = Integer.parseInt(time.getMinutes());
        String mintLamps = IntStream.range(0, 11)
                .mapToObj(lampIndex -> (lampIndex < minutes / 5) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());

        return mintLamps.replace("YYY", "YYR");
    }

    private DetailedBerlinTime createDetailedBerlinTime(String secondsLamp, String hourLamp, String oneHourLamp, String fiveMinuteLamp, String oneMinuteLamp) {

        return DetailedBerlinTime.builder()
                .secondsLamp(secondsLamp)
                .topFiveHourLamps(hourLamp)
                .bottomOneHourLamps(oneHourLamp)
                .topFiveMinuteLamps(fiveMinuteLamp)
                .bottomOneMinuteLamps(oneMinuteLamp)
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
