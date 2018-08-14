package com.ff.controller;

import com.ff.view.SpecificDateView;

import develop.Weather;

public class SpecificDateController implements Runnable{
	SpecificDateView specificdateView = null;
	
	private static SpecificDateController instance = null;
	
	public static SpecificDateController getInstance() {
		if(instance == null)
			instance = new SpecificDateController();
		return instance;
	}	
	
	static int year = 0;
	public static SpecificDateController getInstance(int searchYear) {
		
		year = searchYear;
		
		if(instance == null)
			instance = new SpecificDateController();
		return instance;
		
		
		
		
	}
	
	private SpecificDateController(){

	}
	
	public void viewShow() {
		specificdateView = SpecificDateView.getInstance();
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
		
		
		
		
		
		String[][] datas1 = Weather.GetPastWeather("108", year, "07"); // 평균온도
		String[][] datas2 = Weather.GetPastWeather("108", year, "08"); // 최고온도
		String[][] datas3 = Weather.GetPastWeather("108", year, "10"); // 최저온도
		String[][] datas4 = Weather.GetPastWeather("108", year, "12"); // 평균습도
		String[][] datas5 = Weather.GetPastWeather("108", year, "21"); // 강수량
		String[][] datas6 = Weather.GetPastWeather("108", year, "28"); // 적설량

		/*
		 * 뷰에서 데이터뷰 실행
		 */
		SpecificDateView.getInstance().dataView(datas1, datas2, datas3, datas4, datas5, datas6);
		
		
	}
}


