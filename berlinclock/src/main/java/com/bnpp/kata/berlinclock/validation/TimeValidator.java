package com.bnpp.kata.berlinclock.validation;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.bnpp.kata.berlinclock.constants.Constants.TIME_IS_EMPTY_ERROR;

@Component
public class TimeValidator {
    public void validateTimeValues(TimeComponent time) {

        if (StringUtils.isEmpty(time.getHours()) || StringUtils.isEmpty(time.getMinutes()) || StringUtils.isEmpty(time.getSeconds())) {
            throw new TimeFormatException(TIME_IS_EMPTY_ERROR);
        }
    }
}
