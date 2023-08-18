package com.ta.ditec.services.exception;

import static com.ta.ditec.services.exception.Type.ERROR;
import static com.ta.ditec.services.exception.Type.INFORMATION;
import static com.ta.ditec.services.exception.Type.VALIDATION;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * The enum Error code.
 */

public enum ErrorCode {

	/**
	 * The Bad request.
	 */
	BAD_REQUEST(4000, "Missing or Invalid information", HttpStatus.OK, VALIDATION),
	/**
	 * The Internal error.
	 */
	INTERNAL_ERROR(2000, "An error has occurred, please try again later", HttpStatus.INTERNAL_SERVER_ERROR, ERROR),
	/**
	 * The Not found.
	 */
	NOT_FOUND(1500, "No data found", HttpStatus.OK, INFORMATION),
	/**
	 * The Resource access error.
	 */
	RESOURCE_ACCESS_ERROR(2002, "Resource cannot be updated", HttpStatus.OK, ERROR),
	/**
	 * The No result.
	 */
	NO_RESULT(2003, "Content Request found no results", HttpStatus.OK, INFORMATION),
	/**
	 * The Optum initilaize error.
	 */
	USER_CRED_VALID(1002, "Either Username or Password is wrong.", HttpStatus.OK, VALIDATION),

	NO_DATA_FOUND(1003, "No data found!!", HttpStatus.OK, INFORMATION),

	ALREADY_EXIST(1004, "User already Exist!!", HttpStatus.OK, VALIDATION),
	ALREADY_EXIST_EMAIL(1006, "User Email already Exist!!", HttpStatus.OK, VALIDATION),
	ALREADY_EXIST_MOBILENUMBER(1007, "User MobileNumber already Exist!!", HttpStatus.OK, VALIDATION),
	EMAIL_NOT_VERIFIED(1008, "Email verification is must", HttpStatus.OK, VALIDATION),

	USER_NOT_FOUND(1005, "Username and password must be there!", HttpStatus.OK, VALIDATION),

	EMAIL_NOT_SENT(1009, "Email couldn't sent", HttpStatus.OK, VALIDATION),
	PASSWORD_EXIST(1010, "current password and new password should not be same", HttpStatus.OK, VALIDATION),
	EMAIL_ALREADY_VERIFIED(1011, "Email Already Verified", HttpStatus.OK, INFORMATION),
	ALREADY_TITLE_EXIST(1012, "Title Already Exist!!", HttpStatus.OK, INFORMATION),
	

	// USER_PASSWORD_NOT_VALID(10005, "password is Wrong!", HttpStatus.OK,
	// VALIDATION),

	USER_NOT_VALID(1000, "not valid", HttpStatus.OK, INFORMATION);
	

	private int code;
	private String errorMsg;
	private HttpStatus status;
	private Type type;

	ErrorCode(int code, String errorMsg, HttpStatus status, Type type) {
		this.code = code;
		this.errorMsg = errorMsg;
		this.status = status;
		this.type = type;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

}
