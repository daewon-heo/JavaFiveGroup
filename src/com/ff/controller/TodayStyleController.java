package com.ff.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import com.ff.view.TodayStyleView;
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import develop.Weather;

public class TodayStyleController implements Runnable {
	
	TodayStyleView TodayStyleView = null;
	
	private static TodayStyleController instance = null;
	
	private TodayStyleController(){
		
	}
	
	private String nowTem;		 // 현재 기온
	private String region;      // 지역
	private String today;	 // 오늘 날짜
	private String high;		 // 최고 기온
	private String low; 		 // 최저 기온
	
	
	public String getNowTem() {
		return nowTem;
	}

	public void setNowTem(String nowTem) {
		this.nowTem = nowTem;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}
	
	
	public static TodayStyleController getInstance() {
		if(instance == null)
			instance = new TodayStyleController();
		return instance;
	}
	
	public void viewShow() {
		TodayStyleView = TodayStyleView.getInstance(seasonStyle());
//		new Thread(this).start();
	}

	
	public String seasonStyle(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) +1;
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
	
	public void weather(){
		System.out.println("weather 시작");
		Map<String, String> awsMap = Weather.GetCurrentWeather("108");
		Calendar cal = new GregorianCalendar();
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d", new Locale("US"));
		nowTem = awsMap.get("기온");
		region = "Seoul";
		today = sdf.format(cal.getTime());
		high = Weather.GetPastWeather("108", cal.getWeekYear(), "08")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
		low = Weather.GetPastWeather("108", cal.getWeekYear(), "10")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
		System.out.println("weather 끝");
	
 }

	@Override
	public void run() {
		System.out.println("thread run start");
		TodayStyleView.getInstance().setWeatherInfo(getInstance());
		TodayStyleView.getInstance().remove(TodayStyleView.getInstance().loadingPanel);
		TodayStyleView.getInstance().refresh();
		System.out.println("thread run end");
	}
}
