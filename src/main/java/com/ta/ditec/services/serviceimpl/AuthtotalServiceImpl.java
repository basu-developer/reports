package com.ta.ditec.services.serviceimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ta.ditec.services.domain.AUTHTransaction;
import com.ta.ditec.services.domain.EKYCTransaction;
import com.ta.ditec.services.repo.AUTHTransactionRepo;
import com.ta.ditec.services.repo.EKYCTransactionRepo;
import com.ta.ditec.services.response.AuthTotalResponse;
import com.ta.ditec.services.response.TodayAuthResponse;
import com.ta.ditec.services.response.TodayEkycResponse;
import com.ta.ditec.services.service.AuthtotalService;

@Service

public class AuthtotalServiceImpl implements AuthtotalService {

	@Autowired
	private AUTHTransactionRepo authTransactionRepo;

	@Autowired
	private EKYCTransactionRepo ekycTransactionRepo;

	@Override
	  @Cacheable(cacheNames = "year-total-cache", key = "'year-total'", cacheManager = "cacheManager")
	public AuthTotalResponse getAuthtotalService() {

		LocalDateTime todayfromDateTime = LocalDateTime.now();
		LocalDateTime todayendDateTime = LocalDateTime.now();

		LocalDateTime fromDateTime = LocalDateTime.of(2023, 07, 01, 00, 00);
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

		List<AUTHTransaction> authlist = authTransactionRepo.findByRequestdateBetween(timestampstart, timestampend);
		List<EKYCTransaction> ekyclist = ekycTransactionRepo.findByRequestdateBetween(timestampstart, timestampend);

		List<AUTHTransaction> todayauthlist = authTransactionRepo.findByRequestdateBetween(todaytimestampstart,
				toadytimestampend);
		List<EKYCTransaction> todayekyclist = ekycTransactionRepo.findByRequestdateBetween(todaytimestampstart,
				toadytimestampend);

		Long AllAuthData = authlist.stream().count();
		Long AllEKYCData = ekyclist.stream().count();

		Long todayAllAuthData = todayauthlist.stream().count();
		Long todayAllEKYCData = todayekyclist.stream().count();

		Long yeartotal = AllAuthData + AllEKYCData;
		Long todaytotal = todayAllAuthData + todayAllEKYCData;

		Long subaua = authlist.stream().map(X -> X.getAgencycode()).distinct().count();
		AuthTotalResponse res = new AuthTotalResponse();

		res.setYearauthtotal(yeartotal);
		res.setTodayauthtotal(todaytotal);
		res.setSubaua(subaua);
		System.out.println("hitting dbbbbb");
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

		List<AUTHTransaction> todayauthotplist = authTransactionRepo.findByRequestdateBetweenAndOtp(todaytimestampstart,
				toadytimestampend, "y");
		List<AUTHTransaction> todayauthbiolist = authTransactionRepo.findByRequestdateBetweenAndBio(todaytimestampstart,
				toadytimestampend, "y");

		Long authotp = todayauthotplist.stream().map(X -> X.getOtp()).count();
		Long authbio = todayauthbiolist.stream().map(X -> X.getBio()).count();
		TodayAuthResponse res = new TodayAuthResponse();
		res.setAuthotp(authotp);
		res.setAuthfingerprint(authbio);
		res.setAuthdemographic((long) 10);
		res.setAuthiris((long) 8);
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

		List<EKYCTransaction> todayekycotplist = ekycTransactionRepo.findByRequestdateBetweenAndOtp(todaytimestampstart,
				toadytimestampend, "y");
		List<EKYCTransaction> todayekycbiolist = ekycTransactionRepo.findByRequestdateBetweenAndBio(todaytimestampstart,
				toadytimestampend, "y");

		Long todayekyclist = todayekycotplist.stream().count();

		TodayEkycResponse res = new TodayEkycResponse();
		res.setEkycfingerprint((long) 10);
		res.setEkyciris((long) 10);
		res.setEkycotp(todayekyclist);
		return res;
	}

	@Override
	public Map<String, TodayEkycResponse> getTodaysubauaEKYCResponseForEachAgency() {
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
		toadytimestampend.setNanos(0);
		Map<String, TodayEkycResponse> responseMap = new HashMap<>();

		List<String> agencyCodes = getUniqueAgencyCodes(ekycTransactionRepo.findAll());

		for (String agencyCode : agencyCodes) {
			List<EKYCTransaction> todayekycotplist = ekycTransactionRepo.findByRequestdateBetweenAndOtpAndAgencycode(
					todaytimestampstart, toadytimestampend, "y", agencyCode);
			List<EKYCTransaction> todayekycbiolist = ekycTransactionRepo.findByRequestdateBetweenAndBioAndAgencycode(
					todaytimestampstart, toadytimestampend, "y", agencyCode);
			Long todayekyclist = todayekycotplist.stream().count();
			Long biolist = todayekycbiolist.stream().count();
			TodayEkycResponse res = new TodayEkycResponse();
			res.setEkycotp(todayekyclist);
			res.setEkycfingerprint(biolist);
			responseMap.put(agencyCode, res);
		}

		return responseMap;
	}

	private List<String> getUniqueAgencyCodes(List<EKYCTransaction> transactions) {
		return transactions.stream().map(EKYCTransaction::getAgencycode).distinct().collect(Collectors.toList());
	}

	@Override
	public Map<String, TodayAuthResponse> getTodaysubauaAuthResponseForEachAgency() {

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

		Map<String, TodayAuthResponse> respmap = new HashMap<>();

		List<String> agencyCodes = getAuthUniqueAgencyCodes(authTransactionRepo.findAll());

		for (String authagencyCode : agencyCodes) {

			List<AUTHTransaction> todayauthotplist = authTransactionRepo.findByRequestdateBetweenAndOtpAndAgencycode(
					todaytimestampstart, toadytimestampend, "y", authagencyCode);
			List<AUTHTransaction> todayauthbiolist = authTransactionRepo.findByRequestdateBetweenAndBioAndAgencycode(
					todaytimestampstart, toadytimestampend, "y", authagencyCode);

			Long todayotplist = todayauthotplist.stream().count();
			Long biolist = todayauthbiolist.stream().count();
			TodayAuthResponse res = new TodayAuthResponse();
			res.setAuthdemographic((long) 10);
			res.setAuthotp(todayotplist);
			res.setAuthfingerprint(biolist);
			res.setAuthiris((long) 10);
			respmap.put(authagencyCode, res);
		}
		return respmap;
	}

	private List<String> getAuthUniqueAgencyCodes(List<AUTHTransaction> authtransaction) {
		return authtransaction.stream().map(AUTHTransaction::getAgencycode).distinct().collect(Collectors.toList());
	}

}
