package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.ditec.services.domain.AUTHTransaction;
import com.ta.ditec.services.domain.EKYCTransaction;
import com.ta.ditec.services.repo.AUTHTransactionRepo;
import com.ta.ditec.services.repo.EKYCTransactionRepo;
import com.ta.ditec.services.response.AuaAuthMonthlyResponse;
import com.ta.ditec.services.response.AuaEKYCMonthlyResponse;
import com.ta.ditec.services.response.AuthSubAuaResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.service.AuthSubAuaTotalService;

@Service
public class AuthSubAuaTotalServiceImpl implements AuthSubAuaTotalService {

	@Autowired
	private AUTHTransactionRepo authTransactionRepo;

	@Autowired
	private EKYCTransactionRepo ekycTransactionRepo;

	@Override
	public AuthSubAuaResponse getAuthSubAuaResponse() {

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

		LocalDateTime todayfromDateTime = LocalDateTime.now();
		LocalDateTime todayendDateTime = LocalDateTime.now();

		Timestamp todaytimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp todaytimestampend = Timestamp.valueOf(todayendDateTime);
		todaytimestampstart.setHours(0);
		todaytimestampstart.setMinutes(0);
		todaytimestampstart.setSeconds(0);
		todaytimestampstart.setNanos(0);

		todaytimestampend.setHours(23);
		todaytimestampend.setMinutes(59);
		todaytimestampend.setSeconds(59);
		todaytimestampend.setNanos(0);

		Long authtotalCount = authTransactionRepo.countByRequestdateBetweenAndAgencycode(timestampstart, timestampend,
				"Praveen123");
		Long ekyctotalCount = ekycTransactionRepo.countByRequestdateBetweenAndAgencycode(todaytimestampstart,
				todaytimestampend, "Praveen123");

		Long todayauthtotalCount = authTransactionRepo.countByRequestdateBetweenAndAgencycode(todaytimestampstart,
				todaytimestampend, "Praveen123");

		Long total = authtotalCount + ekyctotalCount;

		AuthSubAuaResponse res = new AuthSubAuaResponse();

		res.setSubauaauthtodaytrans(todayauthtotalCount);
		res.setSubauaekyctodaytrans(ekyctotalCount);
		res.setSubauatotaltrans(total);
		return res;
	}

	@Override
	public TodayEkycResponse getTodayEkycResponse() {

		LocalDateTime todayfromDateTime = LocalDateTime.now();
		LocalDateTime todayendDateTime = LocalDateTime.now();
		Timestamp todaytimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp toadytimestampend = Timestamp.valueOf(todayendDateTime);
		todaytimestampstart.setHours(0);
		todaytimestampstart.setMinutes(0);
		todaytimestampstart.setSeconds(0);
		todaytimestampstart.setNanos(0);
		toadytimestampend.setHours(23);
		toadytimestampend.setMinutes(59);
		toadytimestampend.setMinutes(59);
		toadytimestampend.setNanos(000);

		List<EKYCTransaction> todayekycotplist = ekycTransactionRepo
				.findByRequestdateBetweenAndOtpAndAgencycode(todaytimestampstart, toadytimestampend, "y", "Praveen123");
		List<EKYCTransaction> todayekycbiolist = ekycTransactionRepo
				.findByRequestdateBetweenAndBioAndAgencycode(todaytimestampstart, toadytimestampend, "y", "Praveen123");

		Long todayekyclist = todayekycotplist.stream().count();

		TodayEkycResponse res = new TodayEkycResponse();

		res.setEkycfingerprint(null);
		res.setEkyciris(null);
		res.setEkycotp(todayekyclist);
		return res;

	}

	@Override
	public TodayAuthResponse getTodayAuthResponse() {

		LocalDateTime todayfromDateTime = LocalDateTime.now();
		LocalDateTime todayendDateTime = LocalDateTime.now();
		Timestamp todaytimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp toadytimestampend = Timestamp.valueOf(todayendDateTime);
		todaytimestampstart.setHours(0);
		todaytimestampstart.setMinutes(0);
		todaytimestampstart.setSeconds(0);
		todaytimestampstart.setNanos(0);
		toadytimestampend.setHours(23);
		toadytimestampend.setMinutes(59);
		toadytimestampend.setMinutes(59);
		toadytimestampend.setNanos(000);

		List<AUTHTransaction> todayauthotplist = authTransactionRepo
				.findByRequestdateBetweenAndOtpAndAgencycode(todaytimestampstart, toadytimestampend, "y", "Praveen123");
		List<AUTHTransaction> todayauthbiolist = authTransactionRepo
				.findByRequestdateBetweenAndBioAndAgencycode(todaytimestampstart, toadytimestampend, "y", "Praveen123");

		Long todayauthlist = todayauthotplist.stream().count();

		TodayAuthResponse res = new TodayAuthResponse();
		res.setAuthdemographic(todayauthlist);

		return res;

	}

	@Override
	public AuaAuthMonthlyResponse getAuaAuthMonthlyResponse() {

		LocalDateTime fromDateTime = LocalDateTime.now().minusMonths(1);
		LocalDateTime endDateTime = LocalDateTime.now();

		Timestamp timestampstart = Timestamp.valueOf(fromDateTime);
		Timestamp timestampend = Timestamp.valueOf(endDateTime);
		timestampstart.setHours(0);
		timestampstart.setMinutes(0);
		timestampstart.setSeconds(0);
		timestampend.setHours(23);
		timestampend.setMinutes(59);
		timestampend.setSeconds(59);

		List<AUTHTransaction> data = authTransactionRepo.findByRequestdateBetweenAndAgencycode(timestampstart,
				timestampend, "Praveen123");

		AuaAuthMonthlyResponse res = new AuaAuthMonthlyResponse();

		res.setAuthdates(new ArrayList<>());
		res.setAuthfingerprint(new ArrayList<>());
		res.setAuthiris(new ArrayList<>());
		res.setAuthotp(new ArrayList<>());

		Map<String, Integer> fingerprintCountMap = new HashMap<>();
		Map<String, Integer> irisCountMap = new HashMap<>();
		Map<String, Integer> otpCountMap = new HashMap<>();

		for (AUTHTransaction transaction : data) {
			String date = transaction.getRequestdate().toLocalDateTime().toLocalDate().toString();

			// Calculate counts for each authentication method
			if (transaction.getBio() != null && transaction.getBio().equals("y")) {
				fingerprintCountMap.put(date, fingerprintCountMap.getOrDefault(date, 0) + 1);
			}

//	            if (transaction.getIris() != null && transaction.getIris().equals("y")) {
//	                irisCountMap.put(date, irisCountMap.getOrDefault(date, 0) + 1);
//	            }

			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
				otpCountMap.put(date, otpCountMap.getOrDefault(date, 0) + 1);
			}
		}

		List<LocalDate> allDates = new ArrayList<>();
		LocalDate currentDate = fromDateTime.toLocalDate();
		while (!currentDate.isAfter(endDateTime.toLocalDate())) {
			allDates.add(currentDate);
			currentDate = currentDate.plusDays(1);
		}

		for (LocalDate date : allDates) {
			String dateString = date.toString();
			res.getAuthdates().add(dateString);
			res.getAuthfingerprint().add(fingerprintCountMap.getOrDefault(dateString, 0));
			res.getAuthiris().add(irisCountMap.getOrDefault(dateString, 0));
			res.getAuthotp().add(otpCountMap.getOrDefault(dateString, 0));
		}

		return res;

	}

	@Override
	public AuaEKYCMonthlyResponse getAuaEkycMonthlyResponse() {

		LocalDateTime fromDateTime = LocalDateTime.now().minusMonths(1);
		LocalDateTime endDateTime = LocalDateTime.now();

		Timestamp timestampstart = Timestamp.valueOf(fromDateTime);
		Timestamp timestampend = Timestamp.valueOf(endDateTime);
		timestampstart.setHours(0);
		timestampstart.setMinutes(0);
		timestampstart.setSeconds(0);
		timestampend.setHours(23);
		timestampend.setMinutes(59);
		timestampend.setSeconds(59);

		List<EKYCTransaction> data = ekycTransactionRepo.findByRequestdateBetweenAndAgencycode(timestampstart,
				timestampend, "Praveen123");

		System.out.println(data.size());

		AuaEKYCMonthlyResponse res = new AuaEKYCMonthlyResponse();

		res.setEkycdates(new ArrayList<>());
		res.setEkycfingerprint(new ArrayList<>());
		res.setEkyciris(new ArrayList<>());
		res.setEkycotp(new ArrayList<>());

		Map<String, Integer> fingerprintCountMap = new HashMap<>();
		Map<String, Integer> irisCountMap = new HashMap<>();
		Map<String, Integer> otpCountMap = new HashMap<>();

		for (EKYCTransaction transaction : data) {
			String date = transaction.getRequestdate().toLocalDateTime().toLocalDate().toString();

			// Calculate counts for each authentication method
			if (transaction.getBio() != null && transaction.getBio().equals("y")) {
				fingerprintCountMap.put(date, fingerprintCountMap.getOrDefault(date, 0) + 1);
			}

//	            if (transaction.getIris() != null && transaction.getIris().equals("y")) {
//	                irisCountMap.put(date, irisCountMap.getOrDefault(date, 0) + 1);
//	            }

			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
				otpCountMap.put(date, otpCountMap.getOrDefault(date, 0) + 1);
			}
		}

		List<LocalDate> allDates = new ArrayList<>();
		LocalDate currentDate = fromDateTime.toLocalDate();
		while (!currentDate.isAfter(endDateTime.toLocalDate())) {
			allDates.add(currentDate);
			currentDate = currentDate.plusDays(1);
		}

		for (LocalDate date : allDates) {
			String dateString = date.toString();
			res.getEkycdates().add(dateString);
			res.getEkycfingerprint().add(fingerprintCountMap.getOrDefault(dateString, 0));
			res.getEkyciris().add(irisCountMap.getOrDefault(dateString, 0));
			res.getEkycotp().add(otpCountMap.getOrDefault(dateString, 0));
		}

		return res;

	}

}
