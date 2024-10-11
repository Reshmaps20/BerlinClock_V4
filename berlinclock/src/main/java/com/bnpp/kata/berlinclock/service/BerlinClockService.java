package com.bnpp.kata.berlinclock.service;

import org.springframework.stereotype.Service;

@Service
public class BerlinClockService {

    public String convertToBerlinTime(String time) {

        String result;
        if (Integer.parseInt(time) % 2 == 0)
            result = "Y";
        else
            result = "O";
        return result;
    }
}
