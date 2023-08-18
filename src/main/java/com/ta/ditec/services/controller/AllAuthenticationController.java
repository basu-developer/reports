package com.ta.ditec.services.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.AuthTotalResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.service.AuthtotalService;

@RestController
@RequestMapping("api/v1")
public class AllAuthenticationController {

	@Autowired
	private AuthtotalService authtotalService;

	@GetMapping("/authtotaldata")

	public ResponseEntity<TaResponse<AuthTotalResponse>> getAuthTotal() {
		AuthTotalResponse resp = authtotalService.getAuthtotalService();
		TaResponse<AuthTotalResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/authtodaydata")
	public ResponseEntity<TaResponse<TodayAuthResponse>> getTodayAuthResponse() {
		TodayAuthResponse resp = authtotalService.getTodayAuthResponse();
		TaResponse<TodayAuthResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/ekyctodaydata")
	public ResponseEntity<TaResponse<TodayEkycResponse>> getTodayEkycResponse() {
		TodayEkycResponse resp = authtotalService.getTodayEkycResponse();
		TaResponse<TodayEkycResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/ekyctodaysubauadata")
	public ResponseEntity<TaResponse<Map<String, TodayEkycResponse>>> getTodayEkycResponses() {
		Map<String, TodayEkycResponse> resp = authtotalService.getTodaysubauaEKYCResponseForEachAgency();
		TaResponse<Map<String, TodayEkycResponse>> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/authtodaysubauadata")
	public ResponseEntity<TaResponse<Map<String, TodayAuthResponse>>> getTodayAuthResponses() {
		Map<String, TodayAuthResponse> resp = authtotalService.getTodaysubauaAuthResponseForEachAgency();
		TaResponse<Map<String, TodayAuthResponse>> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}
}
