package com.ff.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;


import com.ff.view.TodayStyle2View;

public class TodayStyle2Controller {
	TodayStyle2View TodayStyle2View = null;
	
	
	public TodayStyle2Controller(){
		
	}
	
	public void viewShow() {
		
		TodayStyle2View = new TodayStyle2View();
//		String[][] datas = Weather.GetPastWeather("108", 2018, "08");
//		TodayStyleView.dataView(datas);
	}
	
	public String randStyle(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) -1;
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
