package com.ta.ditec.services.response;

import java.util.List;

import com.ta.ditec.services.domain.AUTHTransaction;

import lombok.Data;

@Data

public class AuthMonthDataResponse {

	private List<String> dates;
	private List<Integer> otp;
	private List<Integer> fingerprint;
	private List<Integer> iris;

}
