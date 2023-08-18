package com.ta.ditec.services.service;

import com.ta.ditec.services.response.AuaAuthMonthlyResponse;
import com.ta.ditec.services.response.AuaEKYCMonthlyResponse;
import com.ta.ditec.services.response.AuthSubAuaResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;

public interface AuthSubAuaTotalService {

	public AuthSubAuaResponse getAuthSubAuaResponse();

	public TodayEkycResponse getTodayEkycResponse();

	public TodayAuthResponse getTodayAuthResponse();

	public AuaAuthMonthlyResponse getAuaAuthMonthlyResponse();

	public AuaEKYCMonthlyResponse getAuaEkycMonthlyResponse();

}
