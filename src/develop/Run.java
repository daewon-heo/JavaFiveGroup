package develop;

import java.util.Map;

public class Run {

	public static void main(String[] args) {
		String stn = "108";
		int year = 1960;
		String obs = "07";
		Map<String, String> awsMap = Weather.GetCurrentWeather(stn);
		System.out.println("현재 기온 : " + awsMap.get("기온"));
		
		
//
//		boolean check = Weather.CheckParameter(stn, year, obs);
		String[][] cal = null;
		cal = Weather.GetPastWeather(stn, year, obs);

		if (cal != null) {

			for (int i = 0; i < cal.length; i++) {
				for (int j = 0; j < cal[0].length; j++) {
					System.out.print(cal[i][j] + "\t");
				}
				System.out.println();
			}
		}else {
			System.out.println("파라미터를 확인하세요");
		}
		System.out.println("프로그램 종료");
	}
}
