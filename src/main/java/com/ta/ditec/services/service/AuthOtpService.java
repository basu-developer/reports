package com.ta.ditec.services.service;

import java.util.List;

import com.ta.ditec.services.domain.AuthOtp;
import com.ta.ditec.services.response.YearlyEKYCCountResponse;

public interface AuthOtpService {

	List<AuthOtp> getAuthOtpServiceAllData();
	
	public YearlyEKYCCountResponse getyeardata();
}
