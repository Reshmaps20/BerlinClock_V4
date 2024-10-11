package com.bnpp.kata.berlinclock.controller;

import com.bnpp.kata.berlinclock.model.BerlinClockRequest;
import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.service.BerlinClockService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.BerlinclockApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BerlinClockController implements BerlinclockApi {

    private final BerlinClockService berlinClockService;

    @Override
    @PostMapping("/berlinclock")
    public ResponseEntity<BerlinClockResponse> calculateBerlinClockTime(BerlinClockRequest request) {
        return new ResponseEntity<>(berlinClockService.convertToBerlinTime(request.getTime()), HttpStatus.OK);
    }
}
