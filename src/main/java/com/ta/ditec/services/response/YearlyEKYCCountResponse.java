package com.ta.ditec.services.response;

import lombok.Data;

@Data
public class YearlyEKYCCountResponse {

	private Long success;
	private Long pending;
	private Long failure;
	private Long total;
	
	
}
