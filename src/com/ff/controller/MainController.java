package com.ff.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import com.ff.view.MainView;

import develop.*;

public class MainController {
	MainView mainView = null;

	private Calendar cal;    
	private String nowTem;		 // 현재 기온
	private boolean rain;	 // 강수 여부
	private String high;		 // 최고 기온
	private String low; 		 // 최저 기온
	private String humidity;	 // 상대 습도
	private String today;	 // 오늘 날짜
	private String day;
	private String iconName;
	

	

	/*public MainController(){

		cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일"); // 현재시간
		SimpleDateFormat wday = new SimpleDateFormat("E요일");
		today = sdf.format(cal.getTime());
		day = wday.format(cal.getTime());

	}*/
	
	public MainController(){

	}
	
	public void getDatas(){
		iconName = "sun";
		cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일"); // 현재 시간
		today = sdf.format(cal.getTime());
		SimpleDateFormat wday = new SimpleDateFormat("E요일");
		day = wday.format(cal.getTime());
		
		Map<String, String> awsMap = Weather.GetCurrentWeather("108");

		nowTem = awsMap.get("기온"); // 현재 기온
		rain = Boolean.parseBoolean(awsMap.get("강수감지"));
		high = Weather.GetPastWeather("108", cal.getWeekYear(), "08")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
		low = Weather.GetPastWeather("108", cal.getWeekYear(), "10")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
		System.out.println(cal.get(Calendar.DATE) +",,"+ ((cal.get(Calendar.MONTH))+1) );
		System.out.println("기온" +  nowTem);
		humidity = Weather.GetPastWeather("108", cal.getWeekYear(), "12")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
		System.out.println("습도"+humidity);
	}
	
	
	
//	public MainController(){
//		iconName = "sun";
//		cal = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 MM월 dd일"); // 현재 시간
//		today = sdf.format(cal.getTime());
//		SimpleDateFormat wday = new SimpleDateFormat("E요일");
//		day = wday.format(cal.getTime());
//		
//		Map<String, String> awsMap = Weather.GetCurrentWeather("108");
//
//		nowTem = awsMap.get("기온"); // 현재 기온
//		rain = Boolean.parseBoolean(awsMap.get("강수감지"));
//		high = Weather.GetPastWeather("108", cal.getWeekYear(), "08")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
//		low = Weather.GetPastWeather("108", cal.getWeekYear(), "10")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
//		System.out.println(cal.get(Calendar.DATE) +",,"+ ((cal.get(Calendar.MONTH))+1) );
//		System.out.println("기온" +  nowTem);
//		humidity = Weather.GetPastWeather("108", cal.getWeekYear(), "12")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];
//		
//		
//	}

	public String getIconName(){
		if(rain == true){
			iconName = "rain";
		}
		
		return iconName;
	}

	public Calendar getCal() {
		return cal;
	}

	public String getDay(){
		return day; 
	}

	public String getNowTem() {
		return nowTem;
	}



	public boolean isRain() {
		return rain;
	}



	public String getHigh() {
		return high;
	}



	public String getLow() {
		return low;
	}



	public String getHumidity() {
		return humidity;
	}



	public String getToday() {
		return today;
	}



	public void setCal(Calendar cal) {
		this.cal = cal;
	}



	public void setNowTem(String nowTem) {
		this.nowTem = nowTem;
	}



	public void setRain(boolean rain) {
		this.rain = rain;
	}



	public void setHigh(String high) {
		this.high = high;
	}



	public void setLow(String low) {
		this.low = low;
	}



	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}



	public void setToday(String today) {
		this.today = today;
	}



	public void viewShow() {
		mainView = new MainView();
		
	}
}
