package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BerlinClockServiceTest {

    private BerlinClockService berlinClockService;
    private static final String TWO = "02";
    private static final String FIVE = "05";
    private static final String YELLOW = "Y";
    private static final String OFF = "O";
    @BeforeEach
    public void setup() {
        berlinClockService = new BerlinClockService();
    }

    @Test
    @DisplayName("Seconds Lamp : should be ON for even seconds")
    public void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(TimeComponent.builder().seconds(TWO).build());

        assertThat(result.getBerlinTime()).contains(YELLOW);
    }

    @Test
    @DisplayName("Seconds Lamp : should be OFF for odd seconds")
    public void convertToBerlinTime_passOddSeconds_secondsLampShouldBeOFF() {

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(TimeComponent.builder().seconds(FIVE).build());

        assertThat(result.getBerlinTime()).contains(OFF);
    }
}
