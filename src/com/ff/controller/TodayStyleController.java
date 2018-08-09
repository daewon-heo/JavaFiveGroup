package com.ff.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ff.view.TodayStyleView;

import develop.Weather;

public class TodayStyleController {
	TodayStyleView TodayStyleView = null;
	
	public TodayStyleController(){
		System.out.println("안녕");
	}
	
	public void viewShow() {
		TodayStyleView = new TodayStyleView(randStyle());
//		String[][] datas = Weather.GetPastWeather("108", 2018, "08");
//		TodayStyleView.dataView(datas);
	}
	
	public String randStyle(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.DAY_OF_MONTH) -1;
		System.out.println("현재 월 : " + iSeason);
		
		String sSeason = null;
		
		switch (iSeason) {
		case 12:
		case 1:
		case 2:
			sSeason = "winter";
			break;
		case 3:
		case 4:
		case 5:
			sSeason = "spring";
			break;
		case 6:
		case 7:
		case 8:
			sSeason = "summer";
			break;
		case 9:
		case 10:
		case 11:
			sSeason = "fall";
			break;
		default:
			break;
		}
		String stylePath = "datas/images/style/" + sSeason +  "/" + String.valueOf((int)(Math.random()*9)+1).substring(0,1) + ".jpg";
		
		return stylePath;
		
	}
}
