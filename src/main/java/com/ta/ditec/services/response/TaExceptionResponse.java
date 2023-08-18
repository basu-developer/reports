package com.ta.ditec.services.response;

import lombok.Data;
import com.ta.ditec.services.exception.Type;

@Data
public class TaExceptionResponse {

	private int code;
	private String message;
	private Type type;

}
