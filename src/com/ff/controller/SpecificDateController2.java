package com.ff.controller;

import com.ff.view.SpecificDateView2;

import develop.Weather;

public class SpecificDateController2 implements Runnable{
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

	@Override
	public void run() {
		/*
		 * 데이터 가져오는 코드 작성
		 */
		String[][] datas1 = Weather.GetPastWeather("108", 2017, "10");
		String[][] datas2 = Weather.GetPastWeather("108", 2017, "10");
		String[][] datas3 = Weather.GetPastWeather("108", 2017, "10");
		String[][] datas4 = Weather.GetPastWeather("108", 2017, "10");

		/*
		 * 뷰에서 데이터뷰 실행
		 */
		SpecificDateView2.getInstance().dataView(datas1);
		
	}
}


