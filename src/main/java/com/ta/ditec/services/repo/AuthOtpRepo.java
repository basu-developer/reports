package com.ta.ditec.services.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ta.ditec.services.domain.AuthOtp;

@Repository
public interface AuthOtpRepo extends JpaRepository<AuthOtp, Long> {

//	Long findByRequestdateBetween(Timestamp timestampstart, Timestamp timestampend);

	long countByRequestdateBetween(Timestamp startDate, Timestamp endDate);

	long countByRequestdateBetweenAndErrorcodeAndErrormsg(Timestamp startDate, Timestamp endDate, String errorCode,
			String errorMsg);

	
	

}
