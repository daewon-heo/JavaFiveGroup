package com.ff.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ff.view.TodayStyleView2;
import com.ff.view.TodayStyle2View2;

public class TodayStyleController2 {
	TodayStyleView2 TodayStyleView = null;
	
	private static TodayStyleController2 instance = null;
	
	private TodayStyleController2(){
		
	}
	
	public static TodayStyleController2 getInstance() {
		if(instance == null)
			instance = new TodayStyleController2();
		return instance;
	}
	
	public void viewShow() {
		TodayStyleView = TodayStyleView2.getInstance(seasonStyle());
	}
	
	public String seasonStyle(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) +1;
		
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
