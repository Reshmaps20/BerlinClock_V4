package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.springframework.stereotype.Service;

@Service
public class BerlinClockService {

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        String secondsLamp = (Integer.parseInt(time.getSeconds()) % 2 == 0) ? "Y" : "O";

        return BerlinClockResponse.builder()
                .berlinTime(secondsLamp)
                .build();
    }
}
