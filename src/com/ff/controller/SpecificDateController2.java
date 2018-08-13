package com.ff.controller;

import com.ff.view.SpecificDateView2;

public class SpecificDateController2 {
	SpecificDateView2 specificdateView = null;
	
	private static SpecificDateController2 instance = null;
	
	public static SpecificDateController2 getInstance() {
		if(instance == null)
			instance = new SpecificDateController2();
		return instance;
	}
	
	private SpecificDateController2(){

	}
	
	public void viewShow() {
		specificdateView = SpecificDateView2.getInstance();
	}
	
	public String[][] getPastWeather(){
		String[][] datas = {{" ",	"1월",	"2월"},
							{"1일",	"10",	"20"},
							{"2일",	"30","20"}};
		return datas;
	}
}
