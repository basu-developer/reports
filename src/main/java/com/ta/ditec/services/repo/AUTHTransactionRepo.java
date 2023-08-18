package com.ta.ditec.services.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ta.ditec.services.domain.AUTHTransaction;
import com.ta.ditec.services.domain.EKYCTransaction;

@Repository
public interface AUTHTransactionRepo extends JpaRepository<AUTHTransaction, Long> {

	Long countByRequestdateBetweenAndErrorcode(Timestamp timestampstart, Timestamp timestampend, String string);

	Long countByRequestdateBetweenAndErrorcodeNot(Timestamp timestampstart, Timestamp timestampend, String string);

	Long countByRequestdateBetween(Timestamp timestampstart, Timestamp timestampend);

	List<AUTHTransaction> findByRequestdateBetween(Timestamp timestampstart, Timestamp timestampend);

	List<AUTHTransaction> findByRequestdateBetweenAndOtp(Timestamp timestampstart, Timestamp timestampend,
			String string);

	List<AUTHTransaction> findByRequestdateBetweenAndBio(Timestamp todaytimestampstart, Timestamp toadytimestampend,
			String string);

	List<AUTHTransaction> findByRequestdateBetweenAndOtpAndAgencycode(Timestamp todaytimestampstart,
			Timestamp toadytimestampend, String string, String string2);

	List<AUTHTransaction> findByRequestdateBetweenAndBioAndAgencycode(Timestamp todaytimestampstart,
			Timestamp toadytimestampend, String string, String string2);

	Long countByRequestdateBetweenAndAgencycode(Timestamp timestampstart, Timestamp timestampend, String string);

	List<AUTHTransaction> findByRequestdateBetweenAndAgencycode(Timestamp timestampstart, Timestamp timestampend,
			String string);

}
