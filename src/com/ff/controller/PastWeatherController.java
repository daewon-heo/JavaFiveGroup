package com.ff.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.ff.view.PastWeatherView;

import develop.Weather;

public class PastWeatherController {
	PastWeatherView view1View = null;
	
	public PastWeatherController(){
		
	}
	
	public void viewShow() {
		
		Calendar cal = new GregorianCalendar();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		
		
		String[][] datas = Weather.GetPastWeather("108", year-1, "90");
		
		System.out.println("년 : " + (year-1));
		System.out.println("day : " + day + " month : " + month);
		
		int num = weather(datas[day][month]);
		
		String str = weatherIcon(num);
		
		view1View = new PastWeatherView(str);
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
	
	public int weather(String temp){
		int result = 0;

		if (temp == null) {
			result = 10;
		} else {
			String[] str = temp.split(" ");

			for (int i = 0; i < str.length; i++) {
				System.out.println(str[i]);
				if (str[i].equals("비") || str[i].equals("소나기")) {
					result = 4;
					break;
				} else if (str[i].equals("햇무리")) {
					result = 10;
				} else if (str[i].equals("눈")) {
					result = 7;
				} else {
					result = 1;
				}
			}
		}

		return result;
	}
}
