package com.ff.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.ff.view.PastWeatherView;

import develop.Weather;

public class PastWeatherController {
	
	private boolean isRain;
	private PastWeatherView view1View = null;
	
	public PastWeatherController(){
		
	}
	
	public void viewShow() {
		
		getWeatherData();
		
	}
	
	public void getWeatherData(){
		
		// ================== 과거 날씨로 아이콘 가져오기
		Calendar cal = new GregorianCalendar();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String[][] datas1 = Weather.GetPastWeather("108", year-1, "90");
		
		System.out.printf("%d년 %d월 %d일", year-1, month, day);
		
		int num = pastWeather(datas1[day][month]);
		
		String pastIcon = weatherIcon(num);
		
		// ================== 작년 최고 온도
		String[][] datas2 = Weather.GetPastWeather("108", year-1, "08");
		String temperature = datas2[day][month] + " ℃";
		System.out.println(temperature + " ℃");
		
		// ================== 작년 습도
		String[][] datas3 = Weather.GetPastWeather("108", year-1, "12");
		String humidity = datas3[day][month] +" %";
		System.out.println(humidity +" %");
		
		// ================== 현재 날씨로 아이콘 가져오기
		Map temp = new HashMap();
		
		temp = Weather.GetCurrentWeather("108");
		
		String str1 = (String)temp.get("강수감지");
		
		int g = presentWeather(str1);
		
		String presentIcon = weatherIcon(g);
		
		// ================== 현재 온도
		String ta = temp.get("기온") + " ℃";
		// ================== 현재 습도
		String hm = temp.get("습도") +" %";
		// 현재날씨 아이콘 과거날씨 아이콘 매개변수로 넘겨주기
		view1View = new PastWeatherView(pastIcon, presentIcon, temperature, humidity, ta, hm);
		
	}
	
	public String compareTemperature(){
		// 작년 온도와 현재 온도를 비교해서
		// 현재 온도가 작년보다 얼마나 높거나 낮은지 리턴 해준다.
		String str = "";
		// if( 작년온도 > 현재온도){
		//		str = "오늘이 작년보다 "+" 도 더 낮습니다.";
		// } else if ( 작년온도 == 현재온도){
		// 		str = "오늘은 작년과 같은 온도 입니다.";
		// } else {
		// 		str = "오늘이 작년보다 "+" 도 더 높습니다.";
		// }
			str = "오늘이 작년보다 "+" 도 더 낮습니다.";
		return str;
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
