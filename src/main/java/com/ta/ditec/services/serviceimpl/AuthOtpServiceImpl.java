package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.ditec.services.domain.AuthOtp;
import com.ta.ditec.services.repo.AuthOtpRepo;
import com.ta.ditec.services.response.YearlyEKYCCountResponse;
import com.ta.ditec.services.service.AuthOtpService;

@Service
public class AuthOtpServiceImpl implements AuthOtpService {

	@Autowired
	private AuthOtpRepo authOtpRepo;

	@Override
	public List<AuthOtp> getAuthOtpServiceAllData() {

		List<AuthOtp> otp = authOtpRepo.findAll();
		return otp;
	}

	@Override
	public YearlyEKYCCountResponse getyeardata() {

		LocalDateTime fromDateTime = LocalDateTime.of(2023, 07, 01, 00, 00);
		LocalDateTime endDateTime = LocalDateTime.now();

		System.out.println(fromDateTime);

		Timestamp timestampstart = Timestamp.valueOf(fromDateTime);
		Timestamp timestampend = Timestamp.valueOf(endDateTime);
		timestampstart.setHours(0);
		timestampstart.setMinutes(0);
		timestampstart.setSeconds(0);
		timestampstart.setNanos(000);

		timestampend.setHours(23);
		timestampend.setMinutes(59);
		timestampend.setSeconds(59);
		timestampend.setNanos(000);
		Long count = authOtpRepo.countByRequestdateBetween(timestampstart, timestampend);
		Long success = authOtpRepo.countByRequestdateBetweenAndErrorcodeAndErrormsg(timestampstart, timestampend, "000", "Success");
		YearlyEKYCCountResponse res = new YearlyEKYCCountResponse();
		res.setTotal(count);
		res.setSuccess(success);
		return res;
	}

}
