package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.ditec.services.repo.AUTHTransactionRepo;
import com.ta.ditec.services.response.AUTHTransactionResponse;
import com.ta.ditec.services.service.AUTHTransactionService;

@Service
public class AUTHTransactionServiceImpl implements AUTHTransactionService {

	@Autowired
	private AUTHTransactionRepo authTransactionRepo;

	@Override
	public AUTHTransactionResponse getyeardata() {

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

		Long totalCount = authTransactionRepo.countByRequestdateBetween(timestampstart, timestampend);
		Long successCount = authTransactionRepo.countByRequestdateBetweenAndErrorcode(timestampstart, timestampend,
				"000");
		Long countWithoutErrorCode = authTransactionRepo.countByRequestdateBetweenAndErrorcodeNot(timestampstart,
				timestampend, "000");

		AUTHTransactionResponse response = new AUTHTransactionResponse();
		response.setTotalCount(totalCount);
		response.setSuccessCount(successCount);
		response.setFailureCount(countWithoutErrorCode);

		return response;

	}

	@Override
	public AUTHTransactionResponse gettodayData() {
		LocalDateTime fromDateTime = LocalDateTime.now();
		LocalDateTime endDateTime = LocalDateTime.now();

		Timestamp timestampstart = Timestamp.valueOf(fromDateTime);
		Timestamp timestampend = Timestamp.valueOf(endDateTime);
		timestampstart.setHours(0);
		timestampstart.setMinutes(0);
		timestampstart.setSeconds(0);
		timestampstart.setNanos(0);
		timestampend.setHours(23);
		timestampend.setMinutes(59);
		timestampend.setMinutes(59);
		timestampend.setNanos(000);

		Long totalCount = authTransactionRepo.countByRequestdateBetween(timestampstart, timestampend);
		Long successCount = authTransactionRepo.countByRequestdateBetweenAndErrorcode(timestampstart, timestampend,
				"000");
		Long countWithoutErrorCode = authTransactionRepo.countByRequestdateBetweenAndErrorcodeNot(timestampstart,
				timestampend, "000");

		AUTHTransactionResponse response = new AUTHTransactionResponse();
		response.setTotalCount(totalCount);
		response.setSuccessCount(successCount);
		response.setFailureCount(countWithoutErrorCode);

		return response;

	}

}
