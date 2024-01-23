package com.npn.prj.response.payload;

import com.npn.prj.common.Constant.FwError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {
	private String errorCode ;
	private String errorMessage;
	private Object data;
	public BaseResponse() {
		this.errorCode=FwError.THANHCONG.getCode();
		this.errorMessage=FwError.THANHCONG.getMessage();
	}
	public BaseResponse(String errorCode,String errorMessage) {
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}
	public BaseResponse(FwError fwError) {
		this.errorCode=fwError.getCode();
		this.errorMessage=fwError.getMessage();
	}
	public void setFwError(FwError fwError) {
		this.errorCode=fwError.getCode();
		this.errorMessage=fwError.getMessage();
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
