package com.sbs.demo.vo;

import lombok.Getter;

public class ResultData<DT> { //성공, 실패 여부를 표시 하기 위해 작성
	@Getter
	private String resultCode; // 성공 코드 ex) S-1, S-2 ... 실패 코드 ex)F-1, F-2 ...
	@Getter
	private String msg; //성공, 실패 메세지
	@Getter
	private DT data1; //들어가는 값.
	
	private ResultData() {
		
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-"); //resultCode가 S-로 시작하면 true
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
	
	public static <DT> ResultData<DT> newData(ResultData joinRd, Object newData) {
		return ResultData.from(joinRd.resultCode,joinRd.msg,newData);
	}
}
