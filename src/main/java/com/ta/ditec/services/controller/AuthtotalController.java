package com.ta.ditec.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.ditec.services.exception.Type;
import com.ta.ditec.services.response.AuthTotalResponse;
import com.ta.ditec.services.response.TaResponse;
import com.ta.ditec.services.service.AuthtotalService;

@RestController
@RequestMapping("api/v1")
public class AuthtotalController {

//	@Autowired
//	private AuthtotalService authtotalService;
//
//	@GetMapping("/authtotaldata")
//	public ResponseEntity<TaResponse<AuthTotalResponse>> getAuthTotal() {
//		AuthTotalResponse resp = authtotalService.getAuthtotalService();
//		TaResponse<AuthTotalResponse> res = new TaResponse<>();
//		res.setCode(1000);
//		res.setMessage(null);
//		res.setType(Type.INFORMATION);
//		res.setResponseData(resp);
//		return ResponseEntity.ok(res);
//	}
}
