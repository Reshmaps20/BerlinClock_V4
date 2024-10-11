package com.bnpp.kata.berlinclock.controller;

import com.bnpp.kata.berlinclock.model.BerlinClockRequest;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BerlinClockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Rest API to calculate Berlin Time")
    public void calculateBerlinClockTime_validRequest_shouldReturnBerlinClockResponse() throws Exception {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWENTYTHREE).minutes(FIFTYNINE).seconds(FIFTYNINE).build();
        BerlinClockRequest request = BerlinClockRequest.builder().time(timeComponent).build();

        mockMvc.perform(post("/api/berlinclock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.berlinTime").value(BERLIN_TIME));
    }

    @Test
    @DisplayName("Rest API should throw exception when invalid input is passed")
    public void convertTime_invalidRequest_shouldReturnBadRequest() throws Exception {

        TimeComponent timeComponent = TimeComponent.builder().hours(EMPTY).minutes(FIFTYNINE).seconds(FIFTYNINE).build();
        BerlinClockRequest request = BerlinClockRequest.builder().time(timeComponent).build();

        mockMvc.perform(post("/api/berlinclock").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                        .andExpect(status().isBadRequest());
    }
}
