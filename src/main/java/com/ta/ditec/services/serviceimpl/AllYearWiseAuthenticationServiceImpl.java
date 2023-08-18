package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
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
import com.ta.ditec.services.response.AuthMonthDataResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.response.YearWiseAllAuthResponse;
import com.ta.ditec.services.service.AllYearWiseAuthenticationService;

@Service
public class AllYearWiseAuthenticationServiceImpl implements AllYearWiseAuthenticationService {

	@Autowired
	private AUTHTransactionRepo authTransactionRepo;

	@Autowired
	private EKYCTransactionRepo ekycTransactionRepo;

	@Override
	public YearWiseAllAuthResponse getYearWiseAllAuthResponse() {

		LocalDateTime todayfromDateTime = LocalDateTime.now().minusYears(1);
		LocalDateTime todayendDateTime = LocalDateTime.now();
		Timestamp yeartimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp yeartimestampend = Timestamp.valueOf(todayendDateTime);
		yeartimestampstart.setHours(0);
		yeartimestampstart.setMinutes(0);
		yeartimestampstart.setSeconds(0);
		yeartimestampstart.setNanos(0);
		yeartimestampend.setHours(23);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setNanos(000);

		List<AUTHTransaction> authlist = authTransactionRepo.findByRequestdateBetween(yeartimestampstart,
				yeartimestampend);
		List<EKYCTransaction> ekyclist = ekycTransactionRepo.findByRequestdateBetween(yeartimestampstart,
				yeartimestampend);

		Long AllAuthData = authlist.stream().count();
		Long AllEKYCData = ekyclist.stream().count();
		Long yeartotal = AllAuthData + AllEKYCData;
		YearWiseAllAuthResponse res = new YearWiseAllAuthResponse();
		res.setYearwisetotal(yeartotal);
		res.setYearwiseekyctotal(AllEKYCData);
		res.setYearwiseauthtotal(AllAuthData);
		return res;
	}

	@Override
	public TodayAuthResponse getyearAuthResponse() {

		LocalDateTime todayfromDateTime = LocalDateTime.now().minusYears(1);
		LocalDateTime todayendDateTime = LocalDateTime.now();
		Timestamp yeartimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp yeartimestampend = Timestamp.valueOf(todayendDateTime);
		yeartimestampstart.setHours(0);
		yeartimestampstart.setMinutes(0);
		yeartimestampstart.setSeconds(0);
		yeartimestampstart.setNanos(0);
		yeartimestampend.setHours(23);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setNanos(000);

		List<AUTHTransaction> authotplist = authTransactionRepo.findByRequestdateBetweenAndOtp(yeartimestampstart,
				yeartimestampend, "y");

		List<AUTHTransaction> authbiolist = authTransactionRepo.findByRequestdateBetweenAndBio(yeartimestampstart,
				yeartimestampend, "y");
		TodayAuthResponse res = new TodayAuthResponse();
		Long otp = authotplist.stream().count();
		Long bio = authbiolist.stream().count();

		res.setAuthdemographic((long) 23024);
		res.setAuthotp(otp);
		res.setAuthfingerprint(bio);
		res.setAuthiris((long) 2356);
		return res;
	}

	@Override
	public TodayEkycResponse getyearEkycResponse() {

		LocalDateTime todayfromDateTime = LocalDateTime.now().minusYears(1);
		LocalDateTime todayendDateTime = LocalDateTime.now();
		Timestamp yeartimestampstart = Timestamp.valueOf(todayfromDateTime);
		Timestamp yeartimestampend = Timestamp.valueOf(todayendDateTime);
		yeartimestampstart.setHours(0);
		yeartimestampstart.setMinutes(0);
		yeartimestampstart.setSeconds(0);
		yeartimestampstart.setNanos(0);
		yeartimestampend.setHours(23);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setMinutes(59);
		yeartimestampend.setNanos(000);

		List<EKYCTransaction> authotplist = ekycTransactionRepo.findByRequestdateBetweenAndOtp(yeartimestampstart,
				yeartimestampend, "y");

		List<EKYCTransaction> authbiolist = ekycTransactionRepo.findByRequestdateBetweenAndBio(yeartimestampstart,
				yeartimestampend, "y");
		TodayEkycResponse res = new TodayEkycResponse();
		Long otp = authotplist.stream().count();
		Long bio = authbiolist.stream().count();
		res.setEkycotp(otp);
		res.setEkycfingerprint(bio);
		res.setEkyciris((long) 10);

		return res;
	}

	@Override
	public AuaAuthMonthlyResponse getAuthMonthDataResponse() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime startDateTime = currentDateTime.minusMonths(11);

		Timestamp timestampStart = Timestamp.valueOf(startDateTime);
		Timestamp timestampEnd = Timestamp.valueOf(currentDateTime);

		List<AUTHTransaction> data = authTransactionRepo.findByRequestdateBetween(timestampStart, timestampEnd);

		AuaAuthMonthlyResponse res = new AuaAuthMonthlyResponse();
		res.setAuthdates(new ArrayList<>());
		res.setAuthfingerprint(new ArrayList<>());
		res.setAuthiris(new ArrayList<>());
		res.setAuthotp(new ArrayList<>());
		res.setAuthdemographic(new ArrayList<>());

		Map<String, Map<String, Integer>> countMap = new HashMap<>();

		for (AUTHTransaction transaction : data) {
			LocalDateTime transactionDate = transaction.getRequestdate().toLocalDateTime();
			String month = transactionDate.getYear() + "-" + String.format("%02d", transactionDate.getMonthValue())
					+ "-01";

			Map<String, Integer> methodCountMap = countMap.computeIfAbsent(month, k -> new HashMap<>());

			if (transaction.getBio() != null && transaction.getBio().equals("y")) {
				methodCountMap.put("fingerprint", methodCountMap.getOrDefault("fingerprint", 0) + 1);
			}

//			if (transaction.getIris() != null && transaction.getIris().equals("y")) {
//				methodCountMap.put("iris", methodCountMap.getOrDefault("iris", 0) + 1);
//			}

			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
				methodCountMap.put("otp", methodCountMap.getOrDefault("otp", 0) + 1);
			}

//			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
//				methodCountMap.put("otp", methodCountMap.getOrDefault("otp", 0) + 1);
//			}
		}

		YearMonth currentMonth = YearMonth.from(startDateTime.toLocalDate());
		YearMonth endMonth = YearMonth.from(currentDateTime.toLocalDate());

		while (!currentMonth.isAfter(endMonth)) {
			String monthString = currentMonth.getYear() + "-" + String.format("%02d", currentMonth.getMonthValue())
					+ "-01";
			res.getAuthdates().add(monthString);

			Map<String, Integer> methodCountMap = countMap.getOrDefault(monthString, Collections.emptyMap());
			res.getAuthfingerprint().add(methodCountMap.getOrDefault("fingerprint", 0));
			res.getAuthiris().add(methodCountMap.getOrDefault("iris", 0));
			res.getAuthotp().add(methodCountMap.getOrDefault("otp", 0));
			res.getAuthdemographic().add(methodCountMap.getOrDefault("demogrphic", 0));

			System.out.println(res.getAuthdates());
			System.out.println(res.getAuthfingerprint());
			System.out.println(res.getAuthotp());
			System.out.println(res.getAuthdemographic());
			currentMonth = currentMonth.plusMonths(1);
		}

		return res;
	}

	@Override
	public AuaEKYCMonthlyResponse getAuaEKYCMonthlyResponse() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime startDateTime = currentDateTime.minusMonths(11);

		Timestamp timestampStart = Timestamp.valueOf(startDateTime);
		Timestamp timestampEnd = Timestamp.valueOf(currentDateTime);

		List<AUTHTransaction> data = authTransactionRepo.findByRequestdateBetween(timestampStart, timestampEnd);

		AuaEKYCMonthlyResponse res = new AuaEKYCMonthlyResponse();
		res.setEkycdates(new ArrayList<>());
		res.setEkycfingerprint(new ArrayList<>());
		res.setEkyciris(new ArrayList<>());
		res.setEkycotp(new ArrayList<>());
//		res.setAuthdemographic(new ArrayList<>());

		Map<String, Map<String, Integer>> countMap = new HashMap<>();

		for (AUTHTransaction transaction : data) {
			LocalDateTime transactionDate = transaction.getRequestdate().toLocalDateTime();
			String month = transactionDate.getYear() + "-" + String.format("%02d", transactionDate.getMonthValue())
					+ "-01";

			Map<String, Integer> methodCountMap = countMap.computeIfAbsent(month, k -> new HashMap<>());

			if (transaction.getBio() != null && transaction.getBio().equals("y")) {
				methodCountMap.put("fingerprint", methodCountMap.getOrDefault("fingerprint", 0) + 1);
			}

//			if (transaction.getIris() != null && transaction.getIris().equals("y")) {
//				methodCountMap.put("iris", methodCountMap.getOrDefault("iris", 0) + 1);
//			}

			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
				methodCountMap.put("otp", methodCountMap.getOrDefault("otp", 0) + 1);
			}

//			if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
//				methodCountMap.put("otp", methodCountMap.getOrDefault("otp", 0) + 1);
//			}
		}

		YearMonth currentMonth = YearMonth.from(startDateTime.toLocalDate());
		YearMonth endMonth = YearMonth.from(currentDateTime.toLocalDate());

		while (!currentMonth.isAfter(endMonth)) {
			String monthString = currentMonth.getYear() + "-" + String.format("%02d", currentMonth.getMonthValue())
					+ "-01";
			res.getEkycdates().add(monthString);

			Map<String, Integer> methodCountMap = countMap.getOrDefault(monthString, Collections.emptyMap());
			res.getEkycfingerprint().add(methodCountMap.getOrDefault("fingerprint", 0));
			res.getEkyciris().add(methodCountMap.getOrDefault("iris", 0));
			res.getEkycotp().add(methodCountMap.getOrDefault("otp", 0));
//			res.getAuthdemographic().add(methodCountMap.getOrDefault("demogrphic", 0));

			System.out.println(res.getEkycdates());
			System.out.println(res.getEkycfingerprint());
			System.out.println(res.getEkycotp());
//			System.out.println(res.getEkycdemographic());
			currentMonth = currentMonth.plusMonths(1);
		}

		return res;
	}

}
