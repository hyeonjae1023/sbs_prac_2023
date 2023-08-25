package com.sbs.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public int memberId;
	public String title;
	public String body;
	
	private String writerName;
	private boolean Exta__actorCanDelete;
	private boolean Exta__actorCanModify;
	
	public String getRegDateForPrint() {
		return regDate.substring(2,16);
	}
	public String getupdateDateForPrint() {
		return updateDate.substring(2,16);
	}
}
