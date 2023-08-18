package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.ditec.services.domain.EKYCTransaction;
import com.ta.ditec.services.repo.EKYCTransactionRepo;
import com.ta.ditec.services.response.EKYCYearlyCountResponse;
import com.ta.ditec.services.service.EKYCTransactionService;

@Service
public class EKYCTransactionServiceImpl implements EKYCTransactionService {

	@Autowired
	private EKYCTransactionRepo kuaTransactionRepo;

	@Override
	public List<EKYCTransaction> getAuthOtpServiceAllData() {
		List<EKYCTransaction> alldata = kuaTransactionRepo.findAll();
		return alldata;
	}

	@Override
	public EKYCYearlyCountResponse getyeardata() {
		LocalDateTime fromDateTime = LocalDateTime.of(2023, 07, 01, 00, 00);
		LocalDateTime endDateTime = LocalDateTime.now();

		System.out.println(fromDateTime);

		Timestamp timestampstart = Timestamp.valueOf(fromDateTime);
		Timestamp timestampend = Timestamp.valueOf(endDateTime);
		timestampstart.setHours(0);
		timestampstart.setMinutes(0);
		timestampstart.setSeconds(0);
		timestampstart.setNanos(0);

		timestampend.setHours(23);
		timestampend.setMinutes(59);
		timestampend.setSeconds(59);
		timestampend.setNanos(0);

		Long totalCount = kuaTransactionRepo.countByRequestdateBetween(timestampstart, timestampend);
		Long successCount = kuaTransactionRepo.countByRequestdateBetweenAndErrorcode(timestampstart, timestampend,
				"000");
		Long countWithoutErrorCode = kuaTransactionRepo.countByRequestdateBetweenAndErrorcodeNot(timestampstart,
				timestampend, "000");

		EKYCYearlyCountResponse response = new EKYCYearlyCountResponse();
		response.setTotalCount(totalCount);
		response.setSuccessCount(successCount);
		response.setFailureCount(countWithoutErrorCode);

		return response;
	}

	@Override
	public EKYCYearlyCountResponse gettodaydata() {
		LocalDateTime fromDateTime = LocalDateTime.now();
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
		Long totalCount = kuaTransactionRepo.countByRequestdateBetween(timestampstart, timestampend);
		Long successCount = kuaTransactionRepo.countByRequestdateBetweenAndErrorcode(timestampstart, timestampend,
				"000");
		Long countWithoutErrorCode = kuaTransactionRepo.countByRequestdateBetweenAndErrorcodeNot(timestampstart,
				timestampend, "000");
		EKYCYearlyCountResponse response = new EKYCYearlyCountResponse();
		response.setTotalCount(totalCount);
		response.setSuccessCount(successCount);
		response.setFailureCount(countWithoutErrorCode);
		return response;
	}

}
