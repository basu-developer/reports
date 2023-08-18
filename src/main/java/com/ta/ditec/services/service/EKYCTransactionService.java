package com.ta.ditec.services.service;

import java.util.List;

import com.ta.ditec.services.domain.EKYCTransaction;
import com.ta.ditec.services.response.EKYCYearlyCountResponse;

public interface EKYCTransactionService {

	List<EKYCTransaction> getAuthOtpServiceAllData();
	
	public EKYCYearlyCountResponse getyeardata();
	
	public EKYCYearlyCountResponse gettodaydata();
}
