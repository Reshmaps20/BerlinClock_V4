package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.validation.TimeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BerlinClockServiceTest {

    private BerlinClockService berlinClockService;

    @BeforeEach
    public void setup() {
        TimeValidator timeValidator= new TimeValidator();
        berlinClockService = new BerlinClockService(timeValidator);
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

    @Test
    @DisplayName("Five Minute Row : third lamp should be RED when given minute is 15")
    public void convertToBerlinTime_passMinutesFifteen_fiveMinuteRowThirdLampShouldBeRed() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FIFTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(THIRD_LAMP_RED_OUT_OF_ELEVEN);
    }

    @Test
    @DisplayName("Five Minute Row : lamps should be YELLOW based on minutes divisible by 5;every third lamp should be RED")
    public void convertToBerlinTime_whenMinutesDivisibleByFive_lampsShouldBeYellowWithThirdLampRed() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWENTY).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(FIVE_MINT_FOURLAMPON);
    }

    @Test
    @DisplayName("One Minute Row : should be OFF when given minute is divisible by 5")
    public void convertToBerlinTime_passMinuteDivisibleByFive_allOneMinuteLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FIVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }

    @Test
    @DisplayName("One Minute Row : first lamp should be YELLOW when minute divided by 5 has reminder 1")
    public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderOne_firstLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SIX).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_LAMP_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : first two lamps should be YELLOW when minute divided by 5 has reminder 2")
    public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderTwo_firstTwoLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWELVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_TWO_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : first three lamp should be YELLOW when minute divided by 5 has reminder 3")
    public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderThree_firstThreeLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(EIGHTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_THREE_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : all lamp should be YELLOW when minute divided by 5 has reminder 4")
    public void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderFour_allLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FOURTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(ALL_FOUR_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hours are empty")
    public void convertToBerlinTime_checkWhetherTheInputHoursAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EMPTY).minutes(FOURTEEN).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minutes are empty")
    public void convertToBerlinTime_checkWhetherTheInputMinutesAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(EMPTY).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input seconds are empty")
    public void convertToBerlinTime_checkWhetherTheInputSecondsAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(EMPTY).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hour is greater than 23")
    public void convertToBerlinTime_passHourGreaterThan23_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SEVENTY).minutes(ZERO).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hour is less than 0")
    public void convertToBerlinTime_passHourLessThanZero_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(MINUS_ONE).minutes(ZERO).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minute is greater than 59")
    public void convertToBerlinTime_passMinuteGreaterThan59_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SEVENTY).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minute is less than 0")
    public void convertToBerlinTime_passMinuteLessThanZero_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(MINUS_ONE).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> berlinClockService.convertToBerlinTime(timeComponent));
    }
}
