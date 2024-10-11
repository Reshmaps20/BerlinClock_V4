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
    private static final String FOURTEEN = "14";
    private static final String EIGHTEEN = "18";
    private static final String TWENTYTHREE = "23";
    private static final String YELLOW = "Y";
    private static final String OFF = "O";
    public static final String FOUR_LAMPS_OFF = "OOOO";
    private static final String FIRST_LAMP_RED = "ROOO";
    public static final String FIRST_TWO_LAMPS_RED = "RROO";
    public static final String FIRST_THREE_LAMPS_RED = "RRRO";
    public static final String ALL_FOUR_LAMPS_RED = "RRRR";
    private static final String ALL_11_LAMPS_OFF = "OOOOOOOOOOO";
    public static final String ONE_LAMP_YELLOW_OUT_OF_ELEVEN = "YOOOOOOOOOO";
    public static final String TWO_LAMP_YELLOW_OUT_OF_ELEVEN = "YYOOOOOOOOO";

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

    @Test
    @DisplayName("One Hour Row : first lamp should be RED when hour divided by 5 has reminder 1")
    public void convertToBerlinTime_whenHourDividedByFiveHasRemainderOne_firstLampShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SIX).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_LAMP_RED);
    }

    @Test
    @DisplayName("One Hour Row : first two lamps should be RED when hour divided by 5 has reminder 2")
    public void convertToBerlinTime_whenHourDividedByFiveHasRemainderTwo_firstTwoLampShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWELVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_TWO_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : first three lamps should be RED when hour divided by 5 has reminder 3")
    public void convertToBerlinTime_whenHourDividedByFiveHasRemainderThree_firstThreeLampShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EIGHTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_THREE_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : all lamps should be RED when hour divided by 5 has reminder 4")
    public void convertToBerlinTime_whenHourDividedByFiveHasRemainderFour_allLampShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(FOURTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(ALL_FOUR_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Minute Row : should be OFF when given minute is less than 5")
    public void convertToBerlinTime_passMinutesLessThanFive_allFiveMinuteLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(ALL_11_LAMPS_OFF);
    }

    @Test
    @DisplayName("Five Minute Row : first lamp should be YELLOW when given minute is between 5 and 9")
    public void convertToBerlinTime_passMinutesBetweenFiveAndNine_firstLampOfFiveMinuteLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SIX).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(ONE_LAMP_YELLOW_OUT_OF_ELEVEN);
    }

    @Test
    @DisplayName("Five Minute Row : first two lamps should be YELLOW when given minute is between 10 and 14")
    public void convertToBerlinTime_passMinutesBetweenTenAndFifteen_firstTwoLampOfFiveMinuteLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWELVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(TWO_LAMP_YELLOW_OUT_OF_ELEVEN);
    }
}
