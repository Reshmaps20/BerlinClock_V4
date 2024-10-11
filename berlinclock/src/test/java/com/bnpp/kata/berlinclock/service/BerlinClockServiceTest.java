package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BerlinClockServiceTest {

    private BerlinClockService berlinClockService;
    private static final String ZERO = "00";
    private static final String TWO = "02";
    private static final String FIVE = "05";
    private static final String SIX = "06";
    private static final String TWELVE = "12";
    private static final String EIGHTEEN = "18";
    private static final String TWENTYTHREE = "23";
    private static final String YELLOW = "Y";
    private static final String OFF = "O";
    public static final String FOUR_LAMPS_OFF = "OOOO";
    private static final String FIRST_LAMP_RED = "ROOO";
    public static final String FIRST_TWO_LAMPS_RED = "RROO";
    public static final String FIRST_THREE_LAMPS_RED = "RRRO";
    public static final String ALL_FOUR_LAMPS_RED = "RRRR";


    @BeforeEach
    public void setup() {
        berlinClockService = new BerlinClockService();
    }

    @Test
    @DisplayName("Seconds Lamp : should be ON for even seconds")
    public void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(TWO).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(YELLOW);
    }

    @Test
    @DisplayName("Seconds Lamp : should be OFF for odd seconds")
    public void convertToBerlinTime_passOddSeconds_secondsLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(FIVE).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(OFF);
    }

    @Test
    @DisplayName("Five Hour Row : should be OFF when given hour is less than 5")
    public void convertToBerlinTime_passHoursLessThanFive_allFiveHourLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWO).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }

    @Test
    @DisplayName("Five Hour Row : first lamp should be RED when given hour is between 5 and 9")
    public void convertToBerlinTime_passHoursBetweenFiveAndNine_firstLambOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SIX).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_LAMP_RED);
    }

    @Test
    @DisplayName("Five Hour Row : first two lamps should be RED when given hour is between 10 and 14")
    public void convertToBerlinTime_passHoursBetweenTenAndFourteen_firstTwoLampOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWELVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_TWO_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Hour Row : first three lamp should be RED when given hour is between 15 and 19")
    public void convertToBerlinTime_passHoursBetweenFifteenAndNineteen_firstThreeLampOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EIGHTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_THREE_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Hour Row : all lamps should be RED when given hour is between 20 and 23")
    public void convertToBerlinTime_passHoursBetweenTwentyToTwentyThree_allLampsOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWENTYTHREE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(ALL_FOUR_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : should be OFF when given hour is divisible by 5")
    public void convertToBerlinTime_passHoursDivisibleByFive_allOneHourLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(FIVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }
}
