package com.ta.ditec.services.service;

import java.util.Map;

import com.ta.ditec.services.response.AuthTotalResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;

public interface AuthtotalService {

	public AuthTotalResponse getAuthtotalService();

	public TodayAuthResponse getTodayAuthResponse();

	public TodayEkycResponse getTodayEkycResponse();

	public Map<String, TodayAuthResponse> getTodaysubauaAuthResponseForEachAgency();

	public Map<String, TodayEkycResponse> getTodaysubauaEKYCResponseForEachAgency();
}
