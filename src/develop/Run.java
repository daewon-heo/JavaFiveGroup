package develop;

public class Run {

	public static void main(String[] args) {
		String stn = "108";
		int year = 2018;
		String obs = "07";
		Weather.GetCurrentWeather();

//		boolean check = Weather.CheckParameter(stn, year, obs);
//		String[][] cal = null;
//		if (check) {
//			cal = Weather.GetPastWeather(stn, year, obs);
//		}
//
//		if (cal != null) {
//
//			for (int i = 0; i < cal.length; i++) {
//				for (int j = 0; j < cal[0].length; j++) {
//					System.out.print(cal[i][j] + "\t");
//				}
//				System.out.println();
//			}
//		}
//		System.out.println("프로그램 종료");
	}
}
