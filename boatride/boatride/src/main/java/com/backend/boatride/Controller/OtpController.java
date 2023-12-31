package com.backend.boatride.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.boatride.service.OtpService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, Object> requestBody) {
        Long userId = Long.parseLong((String) requestBody.get("userId"));
        String enteredOtp = (String) requestBody.get("enteredOtp");
        boolean isOtpValid = otpService.verifyOtp(userId, enteredOtp);

        if (isOtpValid) {
            return ResponseEntity.ok("Verified successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
