package com.bnpp.kata.berlinclock.service;

import static com.bnpp.kata.berlinclock.constants.Constants.*;
import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.store.Lamp;
import com.bnpp.kata.berlinclock.store.LampRow;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BerlinClockService {

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        validateTimeValues(time);
        Map<String, String> lamps = calculateLamps(time);

        return BerlinClockResponse.builder()
                .detailedBerlinTime(createDetailedBerlinTime(lamps))
                .build();
    }

    private void validateTimeValues(TimeComponent time) {

        if (time.getHours() == null || time.getHours().isEmpty()) {
            throw new TimeFormatException(TIME_IS_EMPTY_ERROR);
        }
        if (time.getMinutes() == null || time.getMinutes().isEmpty()) {
            throw new TimeFormatException(TIME_IS_EMPTY_ERROR);
        }
    }

    private Map<String, String> calculateLamps(TimeComponent time) {

        Map<String, String> lamps = new HashMap<>();
        int hours = Integer.parseInt(time.getHours());
        int minutes = Integer.parseInt(time.getMinutes());
        int seconds = Integer.parseInt(time.getSeconds());

        lamps.put(LampRow.SECONDS_LAMP.getName(), getSecondsLamp(seconds));
        lamps.put(LampRow.TOP_HOUR_LAMP.getName(), getHoursLamp(LampRow.TOP_HOUR_LAMP.getLength(), hours / HOUR_DIVIDER));
        lamps.put(LampRow.BOTTOM_HOUR_LAMP.getName(), getHoursLamp(LampRow.BOTTOM_HOUR_LAMP.getLength(), hours % HOUR_DIVIDER));
        lamps.put(LampRow.TOP_MINUTE_LAMP.getName(), getMinuteLamp(LampRow.TOP_MINUTE_LAMP.getLength(), minutes / MINUTES_DIVIDER, true) );
        lamps.put(LampRow.BOTTOM_MINUTE_LAMP.getName(), getMinuteLamp(LampRow.BOTTOM_MINUTE_LAMP.getLength(), minutes % MINUTES_DIVIDER, false));

        return lamps;
    }

    private String getMinuteLamp(int rowLength, int minuteValue, boolean isTopRow) {

        String mintLamps = IntStream.range(ZERO, rowLength)
                .mapToObj(lampIndex -> (lampIndex < minuteValue) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());

        return isTopRow ? mintLamps.replace(REPLACE_YYY, REPLACE_TO_YYR) : mintLamps;
    }

    private DetailedBerlinTime createDetailedBerlinTime(Map<String, String> lamps) {

        return DetailedBerlinTime.builder()
                .secondsLamp(lamps.get(LampRow.SECONDS_LAMP.getName()))
                .topFiveHourLamps(lamps.get(LampRow.TOP_HOUR_LAMP.getName()))
                .bottomOneHourLamps(lamps.get(LampRow.BOTTOM_HOUR_LAMP.getName()))
                .topFiveMinuteLamps(lamps.get(LampRow.TOP_MINUTE_LAMP.getName()))
                .bottomOneMinuteLamps(lamps.get(LampRow.BOTTOM_MINUTE_LAMP.getName()))
                .build();
    }

    private static String getSecondsLamp(int seconds) {
        return (seconds % SECONDS_DIVIDER == ZERO) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
    }

    private static String getHoursLamp(int rowLength, int hourValue) {

        return IntStream.range(ZERO, rowLength)
                .mapToObj(lampIndex -> (lampIndex < hourValue) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());
    }
}
