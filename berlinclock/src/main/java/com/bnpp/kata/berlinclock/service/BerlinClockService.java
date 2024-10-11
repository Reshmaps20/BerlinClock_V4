package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        int hours = Integer.parseInt(time.getHours());
        return IntStream.range(0, 4).mapToObj(lampIndex -> (lampIndex < hours / 5) ? "R" : "O").collect(Collectors.joining());
    }
}
