package develop;

import java.util.Map;

public class Run {

	public static void main(String[] args) {
		String stn = "108";
		int year = 1960;
		String obs = "07";
		Map<String, String> awsMap = Weather.GetCurrentWeather(stn);
		System.out.println(awsMap);
		System.out.println("현재 기온 : " + awsMap.get("기온"));
		
		String[][] pastWeather = null;
		pastWeather = Weather.GetPastWeather(stn, year, obs);

		if (pastWeather != null) {

			for (int i = 0; i < pastWeather.length; i++) {
				for (int j = 0; j < pastWeather[0].length; j++) {
					System.out.print(pastWeather[i][j] + "\t");
				}
				System.out.println();
			}
		}else {
			System.out.println("파라미터를 확인하세요");
		}
		System.out.println("프로그램 종료");
	}
}
