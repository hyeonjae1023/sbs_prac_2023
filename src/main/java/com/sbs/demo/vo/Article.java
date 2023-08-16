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
}
