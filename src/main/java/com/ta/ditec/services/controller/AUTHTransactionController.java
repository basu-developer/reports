package com.ta.ditec.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.AUTHTransactionResponse;
import com.ta.ditec.services.response.EKYCYearlyCountResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.service.AUTHTransactionService;

@RestController
@RequestMapping("api/v1")
public class AUTHTransactionController {

	@Autowired
	private AUTHTransactionService authTransactionService;

	@GetMapping("/authyeartrans")
	ResponseEntity<TaResponse<AUTHTransactionResponse>> getAUTHYearTransactionResponse() {
		AUTHTransactionResponse authres = authTransactionService.getyeardata();
		TaResponse<AUTHTransactionResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage("success");
		res.setType(Type.INFORMATION);
		res.setResponseData(authres);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/authtodaytrans")
	ResponseEntity<TaResponse<AUTHTransactionResponse>> getAUTHTodayTransactionResponse() {
		AUTHTransactionResponse authres = authTransactionService.gettodayData();
		TaResponse<AUTHTransactionResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage("success");
		res.setType(Type.INFORMATION);
		res.setResponseData(authres);
		return ResponseEntity.ok(res);
	}
}
