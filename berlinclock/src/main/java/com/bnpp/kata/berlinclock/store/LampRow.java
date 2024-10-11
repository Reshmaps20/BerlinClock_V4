package com.bnpp.kata.berlinclock.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LampRow {

    SECONDS_LAMP(1, "secondLamp"),
    TOP_HOUR_LAMP(4, "topFiveHourLamps"),
    BOTTOM_HOUR_LAMP(4, "bottomOneHourLamps"),
    TOP_MINUTE_LAMP(11, "topFiveMinuteLamps"),
    BOTTOM_MINUTE_LAMP(4, "bottomOneMinuteLamps");

    private final int length;
    private final String name;
}
