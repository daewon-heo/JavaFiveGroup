package com.ff.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import com.ff.view.MainView;

import develop.*;
import sun.applet.Main;

public class MainController implements Runnable {
	MainView mainView = null;

	private Calendar cal;    
	private String nowTem;		 // 현재 기온
	private boolean rain;	 // 강수 여부
	private String high;		 // 최고 기온
	private String low; 		 // 최저 기온
	private String humidity;	 // 상대 습도
	private String today;	 // 오늘 날짜
	private String day;
	private String iconName = "sun";
	private String nowState;
	private String stateIcon;
	
	public static MainController instance = null;
	public static MainController getInstance(){
		if(instance == null)
			instance = new MainController();
		return instance;
	}
	
	private MainController(){

	}
	
	public void getDatas(){
		iconName = "sun";
		cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 M월 dd일"); // 현재 시간
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
		//humidity = Weather.GetPastWeather("108", cal.getWeekYear(), "12")[cal.get(Calendar.DATE)][cal.get(Calendar.MONTH)+1];

		humidity = awsMap.get("습도") + "%";
		System.out.println("습도"+humidity);
		
		double douNowTep = Double.parseDouble(nowTem);
		int intNowTep = (int)douNowTep;
		if(intNowTep >= 27){
			nowState = "더워요";
			stateIcon = "fire";
		}else if(intNowTep >=16 && intNowTep < 27){
			nowState = "적당해요";
			stateIcon = "smile";
		}else if(intNowTep >= 8 && intNowTep <16){
			nowState = "선선해요";
			stateIcon = "wind";
		}else if(intNowTep <8){
			nowState = "추워요";
			stateIcon = "snowflake";
		}
		
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
			if(Double.parseDouble(nowTem) < 0 ){
				iconName = "snow";
			}
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

	public String getNowState(){
		return nowState;
	}
	
	public String getStateIcon(){
		return stateIcon;
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
		mainView = MainView.getInstance();
		
	}

	@Override
	public void run() {
		System.out.println("과거정보 가져오기 시작");
		String[][] test = Weather.GetPastWeather("108", 2000, "07");
		System.out.println("test : " + test);
		this.getDatas();
		
		MainView.getInstance().remove(MainView.getInstance().loadingPanel);
		
		MainView.getInstance().temComponent(this);
		MainView.getInstance().icon(this);

		MainView.getInstance().repaint();
		MainView.getInstance().revalidate();;
		
		System.out.println("과거정보 가져오기 끝");
		
	}
}
