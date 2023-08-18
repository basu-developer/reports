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
import com.ta.ditec.services.repo.AUTHTransactionRepo;
import com.ta.ditec.services.response.AuthMonthDataResponse;
import com.ta.ditec.services.service.MonthlyDataService;

@Service
public class MonthlyDataServiceImpl implements MonthlyDataService {

    @Autowired
    private AUTHTransactionRepo authTransactionRepo;

    @Override
    public AuthMonthDataResponse getallAuthMonthData() {

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

        List<AUTHTransaction> data = authTransactionRepo.findByRequestdateBetween(timestampstart, timestampend);

        AuthMonthDataResponse res = new AuthMonthDataResponse();

      
        res.setDates(new ArrayList<>());
        res.setFingerprint(new ArrayList<>());
        res.setIris(new ArrayList<>());
        res.setOtp(new ArrayList<>());

        Map<String, Integer> fingerprintCountMap = new HashMap<>();
        Map<String, Integer> irisCountMap = new HashMap<>();
        Map<String, Integer> otpCountMap = new HashMap<>();

        for (AUTHTransaction transaction : data) {
            String date = transaction.getRequestdate().toLocalDateTime().toLocalDate().toString();

            // Calculate counts for each authentication method
            if (transaction.getBio() != null && transaction.getBio().equals("y")) {
                fingerprintCountMap.put(date, fingerprintCountMap.getOrDefault(date, 0) + 1);
            }

//            if (transaction.getIris() != null && transaction.getIris().equals("y")) {
//                irisCountMap.put(date, irisCountMap.getOrDefault(date, 0) + 1);
//            }

            if (transaction.getOtp() != null && transaction.getOtp().equals("y")) {
                otpCountMap.put(date, otpCountMap.getOrDefault(date, 0) + 1);
            }
        }

        // Add missing dates with 0 count for fingerprint and otp
        List<LocalDate> allDates = new ArrayList<>();
        LocalDate currentDate = fromDateTime.toLocalDate();
        while (!currentDate.isAfter(endDateTime.toLocalDate())) {
            allDates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        for (LocalDate date : allDates) {
            String dateString = date.toString();
            res.getDates().add(dateString);
            res.getFingerprint().add(fingerprintCountMap.getOrDefault(dateString, 0));
            res.getIris().add(irisCountMap.getOrDefault(dateString, 0));
            res.getOtp().add(otpCountMap.getOrDefault(dateString, 0));
        }

        return res;
    }
}
