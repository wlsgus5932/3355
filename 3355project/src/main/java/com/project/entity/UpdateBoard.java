package com.project.entity;

import lombok.Data;

@Data
public class UpdateBoard {
	private int bId;
	private String t;
	private String c;
	private String con;
	
	
	public int getbId() {
		return bId;
	}
	public String getT() {
		return t;
	}
	public String getC() {
		return c;
	}
	public String getCon() {
		return con;
	}
	
	
	
}
