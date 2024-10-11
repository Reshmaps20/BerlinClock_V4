package com.bnpp.kata.berlinclock.validation;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.bnpp.kata.berlinclock.constants.Constants.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class TimeValidator {

    private Predicate<Integer> validationRule;
    private String errorMessage;

    public void validate(int value) {
        if (!validationRule.test(value)) {
            throw new TimeFormatException(errorMessage);
        }
    }

    public void validateTimeValues(TimeComponent time) {

        if (isAnyTimeFieldInvalid(time)) {
            throw new TimeFormatException(TIME_IS_EMPTY_ERROR);
        }

        isTimeRangeInvalid(time);
    }

    private void isTimeRangeInvalid(TimeComponent time) {

        List<TimeValidator> validators = Arrays.asList(
                new TimeValidator(val -> val >= ZERO && val <= MAX_HOURS, INVALID_HOUR_ERROR),
                new TimeValidator(val -> val >= ZERO && val <= MAX_MINUTES, INVALID_MINUTE_ERROR),
                new TimeValidator(val -> val >= ZERO && val <= MAX_SECONDS, INVALID_SECOND_ERROR));

        int[] valuesToValidate = new int[]{Integer.parseInt(time.getHours()), Integer.parseInt(time.getMinutes()),
                Integer.parseInt(time.getSeconds())};

        IntStream.range(ZERO, validators.size()).forEach(validatorIndex -> validators.get(validatorIndex).validate(valuesToValidate[validatorIndex]));
    }

    private boolean isAnyTimeFieldInvalid(TimeComponent time) {
        return StringUtils.isEmpty(time.getHours()) || StringUtils.isEmpty(time.getMinutes())
                || StringUtils.isEmpty(time.getSeconds());
    }
}
