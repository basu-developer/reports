package com.ta.ditec.services.service;

import com.ta.ditec.services.response.AuaAuthMonthlyResponse;
import com.ta.ditec.services.response.AuaEKYCMonthlyResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.response.YearWiseAllAuthResponse;

public interface AllYearWiseAuthenticationService {

	public YearWiseAllAuthResponse getYearWiseAllAuthResponse();

	public TodayAuthResponse getyearAuthResponse();

	public TodayEkycResponse getyearEkycResponse();

	public AuaAuthMonthlyResponse getAuthMonthDataResponse();

	public AuaEKYCMonthlyResponse getAuaEKYCMonthlyResponse();

}
