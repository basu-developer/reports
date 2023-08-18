package com.ta.ditec.services.response;

import lombok.Data;

@Data
public class TodayAuthResponse {

	private Long authdemographic;
	private Long authotp;
	private Long authfingerprint;
	private Long authiris;

	

}
