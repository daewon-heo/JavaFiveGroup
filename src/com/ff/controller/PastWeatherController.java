package com.ff.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.ff.view.PastWeatherView;

import develop.Weather;

public class PastWeatherController {
	
	private static double temperature;
	private static double ta;
	private PastWeatherView view1View = null;
	private Calendar cal = new GregorianCalendar();
	private int year;
	private int month;
	private int day;
	
	public PastWeatherController(){
		
	}

	public void viewShow() {
		
		view1View = new PastWeatherView();
		//view1View.loadingView();
		getWeatherData();
		
	}
	
	public void getWeatherData(){
		
		// ================== 과거 날씨로 아이콘 가져오기
		
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		day = cal.get(Calendar.DAY_OF_MONTH);
		
		String[][] datas1 = Weather.GetPastWeather("108", year-1, "90");
		
		int num = pastWeather(datas1[day][month]);
		
		String pastIcon = weatherIcon(num);
		
		// ================== 작년 최고 온도
		String[][] datas2 = Weather.GetPastWeather("108", year-1, "08");
		temperature = Double.parseDouble(datas2[day][month]);
		System.out.println("작년 최고온도  : "+temperature + " ℃");
		
		// ================== 작년 습도
		String[][] datas3 = Weather.GetPastWeather("108", year-1, "12");
		String humidity = datas3[day][month] +" %";
		System.out.println("작년 습도 : " + humidity);
		
		// ================== 현재 날씨로 아이콘 가져오기
		Map<String, String> temp = new HashMap<String, String>();
		
		temp = Weather.GetCurrentWeather("108");
		
		String str1 = temp.get("강수감지");
		
		int g = presentWeather(str1);
		
		String presentIcon = weatherIcon(g);
		
		// ================== 현재 온도
		try{
		ta = Double.parseDouble((String) temp.get("기온"));
		} catch(NumberFormatException e){
			System.out.println("트래픽이 10만건을 초과해서 기온과 습도를 불러올수 없습니다.");
		}
		System.out.println("현재 온도 : " + ta + " ℃");
		
		// ================== 현재 습도
		String hm = temp.get("습도") +" %";
		System.out.println("현재 습도 : " + hm);
		
		
		// 현재날씨 아이콘 과거날씨 아이콘 매개변수로 넘겨주기
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일 E요일");
		
		String presentDate = sdf.format(cal.getTime());
		
		year = cal.get(Calendar.YEAR) -1;
		
		cal.set(year, month -1, day);
	
		String pastDate = sdf.format(cal.getTime());
		
		view1View.detailView(pastIcon, presentIcon, temperature, humidity, ta, hm, presentDate, pastDate);
		
		
	}
	
	public static String compareTemperature(){
		// 작년 온도와 현재 온도를 비교해서
		// 현재 온도가 작년보다 얼마나 높거나 낮은지 리턴 해준다.
		String result = "";
		
		String format = "#.#";
		DecimalFormat df = new DecimalFormat(format); 
		
		if( temperature > ta){
				result = "오늘이 작년보다 "+ df.format((ta-temperature)*-1) +" ℃ 더 낮습니다.";
		 } else if ( temperature < ta ){
			 	result = "오늘이 작년보다 "+ df.format((ta-temperature)) +" ℃ 더 높습니다.";
		 } else {
		 		result = "오늘은 작년과 온도가 같습니다.";
		 }
		return result;
	}
	
	public String weatherIcon(int num){
		// 날씨에 맞는 아이콘을 리턴 해준다.
		String icon = "";
		
		switch(num){
		case 1: icon = "datas/images/cloud.png"; // 흐림
			break;
		case 2: icon = "datas/images/F_cloud.png"; // 흐리면서 번개
			break;
		case 3: icon = "datas/images/flash.png"; // 천둥 번개
			break;
		case 4: icon = "datas/images/R_cloud.png"; // 비 구름
			break;
		case 5: icon = "datas/images/rain.png"; // 비
			break;
		case 6: icon = "datas/images/rainbow.png"; // 무지개
			break;
		case 7: icon = "datas/images/S_cloud.png"; // 눈 구름
			break;
		case 8: icon = "datas/images/S_cloudy.png"; // 약간 흐림
			break;
		case 9: icon = "datas/images/snow.png"; // 눈
			break;
		case 10: icon = "datas/images/sun.png"; // 맑음
			break;
		default: icon = "datas/images/loading.gif"; // 잘못 선택했을 경우
			break;
		}
		
		return icon;
	}
	
	public int pastWeather(String str){
		int result = 0;

		if (str == null) {
			result = 10;
		} else {
			String[] temp = str.split(" ");

			for (int i = 0; i < temp.length; i++) {
				if (temp[i].equals("비") || temp[i].equals("소나기")) {
					result = 4;
					break;
				} else if (temp[i].equals("햇무리")) {
					result = 10;
				} else if (temp[i].equals("눈")) {
					result = 7;
				} else {
					result = 1;
				}
			}
		}

		return result;
	}
	
	public int presentWeather(String str1){
		int result = 10;
		if(str1 == "1") result = 4;
	
		return result;
	}
	
}
