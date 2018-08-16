package develop;

import java.util.Map;

import com.ff.model.Weather;

public class Run {

	public static void main(String[] args) {
		int year = 2018;
		String obs = "20";
		String stn = "108";
		Map<String, String> awsMap = Weather.GetCurrentWeather(stn);
		System.out.println(awsMap);
		System.out.println("현재 기온 : " + awsMap.get("기온"));
		System.out.println("강수감지 : " + awsMap.get("강수감지"));
		System.out.println("습도 : " + awsMap.get("습도"));
		
//		String[][] pastWeather = null;
//		pastWeather = Weather.GetPastWeather(stn, year, obs);
//
//		if (pastWeather != null) {
//
//			for (int i = 0; i < pastWeather.length; i++) {
//				for (int j = 0; j < pastWeather[0].length; j++) {
//					System.out.print(pastWeather[i][j] + "\t");
//				}
//				System.out.println();
//			}
//		}else {
//			System.out.println("파라미터를 확인하세요");
//		}
//		
//		System.out.println("프로그램 종료");
	}
}
