package com.ta.ditec.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.domain.AuthOtp;
import com.ta.ditec.services.response.YearlyEKYCCountResponse;
import com.ta.ditec.services.service.AuthOtpService;

@RestController
public class AuthOtpController {

	@Autowired
	private AuthOtpService authOtpService;

	@GetMapping("/getall")
	public List<AuthOtp> getAuthOtp() {
		List<AuthOtp> get = authOtpService.getAuthOtpServiceAllData();
		return get;
	}

	@GetMapping("/getyearlytransactions")
	public ResponseEntity<YearlyEKYCCountResponse> getYearlyTrans() {
		YearlyEKYCCountResponse bii = authOtpService.getyeardata();

		return ResponseEntity.status(HttpStatus.OK).body(bii);

	}
}
