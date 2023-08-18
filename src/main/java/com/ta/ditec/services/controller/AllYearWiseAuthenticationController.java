package com.ta.ditec.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.AuaAuthMonthlyResponse;
import com.ta.ditec.services.response.AuaEKYCMonthlyResponse;
import com.ta.ditec.services.response.AuthMonthDataResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.response.YearWiseAllAuthResponse;
import com.ta.ditec.services.service.AllYearWiseAuthenticationService;

@RestController
@RequestMapping("api/v1")
public class AllYearWiseAuthenticationController {

	@Autowired
	private AllYearWiseAuthenticationService allYearWiseAuthenticationService;

	@GetMapping("/yearwisealldata")
	public ResponseEntity<TaResponse<YearWiseAllAuthResponse>> getYearWiseAuthResponse() {
		YearWiseAllAuthResponse yearres = allYearWiseAuthenticationService.getYearWiseAllAuthResponse();
		TaResponse<YearWiseAllAuthResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(yearres);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/yearwiseauthdata")
	public ResponseEntity<TaResponse<TodayAuthResponse>> getYearAuthResponse() {
		TodayAuthResponse yearres = allYearWiseAuthenticationService.getyearAuthResponse();
		TaResponse<TodayAuthResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(yearres);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/yearwiseekycdata")
	public ResponseEntity<TaResponse<TodayEkycResponse>> getyearEkycResponse() {
		TodayEkycResponse yearres = allYearWiseAuthenticationService.getyearEkycResponse();
		TaResponse<TodayEkycResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(yearres);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/yearmonthwiseeauthdata")
	public ResponseEntity<TaResponse<AuaAuthMonthlyResponse>> getAuthMonthDataResponse() {
		AuaAuthMonthlyResponse yearres = allYearWiseAuthenticationService.getAuthMonthDataResponse();
		TaResponse<AuaAuthMonthlyResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(yearres);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/yearmonthwiseekycdata")
	public ResponseEntity<TaResponse<AuaEKYCMonthlyResponse>> getAuaEKYCMonthlyResponse() {
		AuaEKYCMonthlyResponse yearres = allYearWiseAuthenticationService.getAuaEKYCMonthlyResponse();
		TaResponse<AuaEKYCMonthlyResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(yearres);
		return ResponseEntity.ok(res);
	}

}
