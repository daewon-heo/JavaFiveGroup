package com.ff.controller;

import com.ff.model.Weather;
import com.ff.view.SpecificDateView;

public class SpecificDateController implements Runnable{
	SpecificDateView specificdateView = null;
	
	private static SpecificDateController instance = null;
	
	public static SpecificDateController getInstance() {
		if(instance == null)
			instance = new SpecificDateController();
		return instance;
	}	
	
	static String date = "";
	public static SpecificDateController getInstance(String searchDate) {
		
		date = searchDate;
		
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
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		
		String[][] params = {
				{"07","     평균온도"},
				{"08","     최고온도"}, 
				{"10","     최저온도"},
				{"12","     평균습도"},
				{"21","     강 수 량 "},
				{"28","     적 설 량 "}
				};
		
		String[] datas = new String[params.length];
		
		for (int i = 0; i < params.length; i++) {
			datas[i] = Weather.GetPastWeather("108", year, params[i][0])[day][month];
		}
		
		SpecificDateView.getInstance().dataView(datas, params);
	}
}


