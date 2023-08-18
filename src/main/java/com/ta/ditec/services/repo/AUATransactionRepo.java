package com.ta.ditec.services.repo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ta.ditec.services.domain.AUTHTransaction;

@Repository
public interface AUATransactionRepo extends JpaRepository<AUTHTransaction, Long> {

	List<Object[]> countByRequestdateBetweenAndOtp(Timestamp timestampstart, Timestamp timestampend, String string);

}
