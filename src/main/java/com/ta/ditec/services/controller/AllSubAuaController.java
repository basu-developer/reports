package com.ta.ditec.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.AuaAuthMonthlyResponse;
import com.ta.ditec.services.response.AuaEKYCMonthlyResponse;
import com.ta.ditec.services.response.AuthSubAuaResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.service.AuthSubAuaTotalService;

@RestController
@RequestMapping("api/v1")
public class AllSubAuaController {

	@Autowired
	private AuthSubAuaTotalService authSubAuaTotalService;

	@GetMapping("/subauatotal")
	public ResponseEntity<TaResponse<AuthSubAuaResponse>> getAuthSubAuaResponse() {
		AuthSubAuaResponse resp = authSubAuaTotalService.getAuthSubAuaResponse();
		TaResponse<AuthSubAuaResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/subauaekycdata")
	public ResponseEntity<TaResponse<TodayEkycResponse>> getTodayEkycResponse() {

		TodayEkycResponse resp = authSubAuaTotalService.getTodayEkycResponse();
		TaResponse<TodayEkycResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);

	}

	@GetMapping("/subauaauthdata")
	public ResponseEntity<TaResponse<TodayAuthResponse>> getTodayAuthResponse() {

		TodayAuthResponse resp = authSubAuaTotalService.getTodayAuthResponse();
		TaResponse<TodayAuthResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);

	}

	@GetMapping("/subauaauthmonthlydata")
	public ResponseEntity<TaResponse<AuaAuthMonthlyResponse>> getAuaAuthMonthlyResponse() {

		AuaAuthMonthlyResponse resp = authSubAuaTotalService.getAuaAuthMonthlyResponse();
		TaResponse<AuaAuthMonthlyResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);

	}

	@GetMapping("/subauaekycmonthlydata")
	public ResponseEntity<TaResponse<AuaEKYCMonthlyResponse>> getAuaEKYCMonthlyResponse() {
		AuaEKYCMonthlyResponse resp = authSubAuaTotalService.getAuaEkycMonthlyResponse();
		TaResponse<AuaEKYCMonthlyResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage(null);
		res.setType(Type.INFORMATION);
		res.setResponseData(resp);
		return ResponseEntity.ok(res);

	}
}
