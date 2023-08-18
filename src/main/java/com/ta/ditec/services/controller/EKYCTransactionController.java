package com.ta.ditec.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.domain.EKYCTransaction;
import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.EKYCYearlyCountResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.service.EKYCTransactionService;

@RestController
@RequestMapping("api/v1")
public class EKYCTransactionController {

	@Autowired
	private EKYCTransactionService kuaTransactionService;

	@GetMapping("/getallkua")
	public List<EKYCTransaction> getAuthOtp() {
		List<EKYCTransaction> get = kuaTransactionService.getAuthOtpServiceAllData();
		return get;
	}

	@GetMapping("/ekycyeartrans")
	public ResponseEntity<TaResponse<EKYCYearlyCountResponse>> getYearlyTrans() {
		EKYCYearlyCountResponse ekyc = kuaTransactionService.getyeardata();
		TaResponse<EKYCYearlyCountResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage("success");
		res.setType(Type.INFORMATION);
		res.setResponseData(ekyc);
		return ResponseEntity.status(HttpStatus.OK).body(res);

	}

	@GetMapping("/ekyctodaytrans")
	public ResponseEntity<TaResponse<EKYCYearlyCountResponse>> getTodayTrans() {
		EKYCYearlyCountResponse ekyc = kuaTransactionService.gettodaydata();
		TaResponse<EKYCYearlyCountResponse> res = new TaResponse<>();
		res.setCode(1000);
		res.setMessage("success");
		res.setType(Type.INFORMATION);
		res.setResponseData(ekyc);
		return ResponseEntity.status(HttpStatus.OK).body(res);

	}
}
