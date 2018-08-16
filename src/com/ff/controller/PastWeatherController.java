package com.ff.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.ff.view.PastWeatherView;
import com.ff.model.Weather;

public class PastWeatherController implements Runnable {

	private static double pastTemper;
	private static double presentTemper;
	private static double pastHm;
	private static double presentHm;
	private PastWeatherView view1View = null;
	public static PastWeatherController instance = null;

	public static PastWeatherController getInstance() {
		if (instance == null)
			instance = new PastWeatherController();
		return instance;
	}

	private PastWeatherController() {

	}

	public void viewShow() {
		view1View = PastWeatherView.getInstance();
		// view1View.loadingView();
		// getWeatherData();
		new Thread(this).start();
	}

	@Override
	public void run() {
		getWeatherData();
		PastWeatherView.getInstance().remove(PastWeatherView.getInstance().loadingPanel);
		PastWeatherView.getInstance().repaint();
		PastWeatherView.getInstance().revalidate();

	}

	public void getWeatherData() {
		Calendar cal = new GregorianCalendar();

		// ================== 과거 날씨로 아이콘 가져오기
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		int num;
		String pastIcon;
		
		if (!((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0))) {
			// 윤년이 아닐 경우
			String[][] datas1 = Weather.GetPastWeather("108", year - 1, "90");

			num = pastWeather(datas1[day][month]);

			// ================== 작년 최고 온도
			String[][] datas2 = Weather.GetPastWeather("108", year - 1, "08");
			pastTemper = Double.parseDouble(datas2[day][month]);
			System.out.println("작년 최고온도  : " + pastTemper + " ℃");

			// ================== 작년 습도
			String[][] datas3 = Weather.GetPastWeather("108", year - 1, "12");
			pastHm = Double.parseDouble(datas3[day][month]);
			System.out.println("작년 습도 : " + pastHm);
		} else {
			// 윤년일 경우
			String[][] datas1 = Weather.GetPastWeather("108", year - 4, "90");

			num = pastWeather(datas1[day][month]);
			
			// ================== 최근 윤년의 최고 온도
			String[][] datas2 = Weather.GetPastWeather("108", year - 4, "08");
			pastTemper = Double.parseDouble(datas2[day][month]);
			System.out.println("작년 최고온도  : " + pastTemper + " ℃");

			// ================== 최근 윤년의 습도
			String[][] datas3 = Weather.GetPastWeather("108", year - 4, "12");
			pastHm = Double.parseDouble(datas3[day][month]);
			System.out.println("작년 습도 : " + pastHm);
		}
		
		pastIcon = weatherIcon(num);
		
		// ================== 현재 날씨로 아이콘 가져오기
		Map<String, String> weatherInfo = new HashMap<String, String>();

		weatherInfo = Weather.GetCurrentWeather("108");

		String str1 = weatherInfo.get("강수감지");

		int g = presentWeather(str1);

		String presentIcon = weatherIcon(g);

		// ================== 현재 온도
		try {
			presentTemper = Double.parseDouble((String) weatherInfo.get("기온"));
		} catch (NumberFormatException e) {
			System.out.println("트래픽이 10만건을 초과해서 기온과 습도를 불러올수 없습니다.");
		}
		System.out.println("현재 온도 : " + presentTemper + " ℃");

		// ================== 현재 습도
		presentHm = Double.parseDouble(weatherInfo.get("습도"));
		System.out.println("현재 습도 : " + presentHm);

		// 현재날씨 아이콘 과거날씨 아이콘 매개변수로 넘겨주기

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 M월 d일 E요일");

		String presentDate = sdf.format(cal.getTime());
		
		
		if (!((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0))) {
			// 윤년이 아닐 경우
		year = cal.get(Calendar.YEAR) - 1;
		} else {
			// 윤년일 경우 4년을 빼서 최근 윤년의 날짜를 표시
		year = cal.get(Calendar.YEAR) - 4;
		}
		cal.set(year, month - 1, day);

		String pastDate = sdf.format(cal.getTime());

		view1View.detailView(pastIcon, presentIcon, pastTemper, presentTemper, pastHm, presentHm, presentDate,
				pastDate);

	}

	public static String compareTemperature() {
		// 작년 온도와 현재 온도를 비교해서
		// 현재 온도가 작년보다 얼마나 높거나 낮은지 리턴 해준다.
		String result = "";

		String format = "#.#";
		DecimalFormat df = new DecimalFormat(format);

		if (pastTemper > presentTemper) {
			result = "오늘이 작년보다 " + df.format((presentTemper - pastTemper) * -1) + " ℃ 더 낮습니다.";
		} else if (pastTemper < presentTemper) {
			result = "오늘이 작년보다 " + df.format((presentTemper - pastTemper)) + " ℃ 더 높습니다.";
		} else {
			result = "오늘은 작년과 온도가 같습니다.";
		}
		return result;
	}

	public String weatherIcon(int num) {
		// 날씨에 맞는 아이콘을 리턴 해준다.
		String icon = "";

		switch (num) {
		case 1:
			icon = "datas/images/cloud.png"; // 흐림
			break;
		case 2:
			icon = "datas/images/F_cloud.png"; // 흐리면서 번개
			break;
		case 3:
			icon = "datas/images/flash.png"; // 천둥 번개
			break;
		case 4:
			icon = "datas/images/R_cloud.png"; // 비 구름
			break;
		case 5:
			icon = "datas/images/rain.png"; // 비
			break;
		case 6:
			icon = "datas/images/rainbow.png"; // 무지개
			break;
		case 7:
			icon = "datas/images/S_cloud.png"; // 눈 구름
			break;
		case 8:
			icon = "datas/images/S_cloudy.png"; // 약간 흐림
			break;
		case 9:
			icon = "datas/images/snow.png"; // 눈
			break;
		case 10:
			icon = "datas/images/sun.png"; // 맑음
			break;
		default:
			icon = "datas/images/loading.gif"; // 잘못 선택했을 경우
			break;
		}

		return icon;
	}

	public int pastWeather(String str) {
		// 과거 날씨 정보를 받아 정수로 바꿔 아이콘으로 넘겨준다.
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

	public int presentWeather(String str1) {
		// 현재 날씨 정보를 받아 정수로 바꿔 아이콘으로 넘겨준다.
		int result = 10;
		if (str1 == "1")
			result = 4;

		return result;
	}

}
