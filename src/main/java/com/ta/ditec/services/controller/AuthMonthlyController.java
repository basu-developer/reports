package com.ta.ditec.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.response.AuthMonthDataResponse;
import com.ta.ditec.services.service.MonthlyDataService;

@RestController
public class AuthMonthlyController {

	@Autowired
	private MonthlyDataService dataService;

	@GetMapping("/getmonth")
	public ResponseEntity<AuthMonthDataResponse> getmonthly() {
		AuthMonthDataResponse month = dataService.getallAuthMonthData();
		return ResponseEntity.ok(month);
	}
}
