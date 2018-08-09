package develop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Weather {

	/**
	 * 현재 날씨 정보 가져오기 공공저작물 출처표시 지역은 서울(강남x, 과거 데이터가 강남이 없다, 서울만 있다) 현재 정각의 시간만
	 * 가져온다.(ex. 16시 00분, 12시 00분...) <br>
	 * <br>
	 * 가져올수 있는 필드 목록<br>
	 * -1분 관측자료(MinuteModel), 지점 번호(awsId), aws시간(awsDt),강수 감지(rnYn),<br>
	 * -15분 누적 강수량(rn15M), 60분 누적 강수량(rn60M),12시간 누적 강수량(rn12H), 일강수량(rnDay),<br>
	 * -기온(ta), 1분 평균 풍향(wd1M), 1분 평균 풍속(ws1M),최대 순간 풍향(wdMax), 최대 순간 풍속(wsMax),<br>
	 * 10분평균 풍향(wd10M), 10분 평균 풍속(ws10M), 습도(hm), 해면 기압(hm) <br>
	 * null을 반환하면 에러 or 잘못된 정보
	 *
	 * @param awsId 지점번호
	 * @return Map<String, String>
	 */
	public static Map<String, String> GetCurrentWeather(String awsId) {
		if (CheckParameter(awsId)) {
			Map<String, String> awsMap = new HashMap<String, String>();

			try {
				final String awsApiUrl = "http://newsky2.kma.go.kr/iros/RetrieveAwsService2/getOneAwsList";
				final String serviceKey = "mxSeDzkLZPTloPLw9fu7PD5G62hSG92WD7NKwFOIs0QnrmQCUHaFtpOTlFzVTAvZ60Efsm22b%2Fhdm9tk66TT7g%3D%3D";

				Calendar cal = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHH00"); // 현재시간
//			System.out.println(sdf.format(cal.getTime()));

				StringBuilder urlBuilder = new StringBuilder(awsApiUrl); 																	// URL
				urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); 										// ServiceKey
//			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); 						// 공공데이터포털에서 받은 인증키
				urlBuilder.append("&" + URLEncoder.encode("awsId", "UTF-8") + "=" + URLEncoder.encode(awsId, "UTF-8")); 					// 지점번호
				urlBuilder.append("&" + URLEncoder.encode("awsDt", "UTF-8") + "=" + URLEncoder.encode(sdf.format(cal.getTime()), "UTF-8")); // 연월일시분
				
				URL url = new URL(urlBuilder.toString());
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-type", "application/json");
//				System.out.println("Response code: " + conn.getResponseCode());
				BufferedReader rd;
				if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
					rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				} else {
					rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				}
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				conn.disconnect();
//				System.out.println(sb.toString());
				Document doc = Jsoup.parse(sb.toString());
				Elements el = doc.getAllElements();
//			System.out.println(el.select("body"));

				awsMap.put("지점번호", el.select("awsId").text()); // awsid = 지역
				awsMap.put("기온", el.select("ta").text()); // 기온
				awsMap.put("강수감지", el.select("rnYn").text()); // 강수감지
				awsMap.put("1분평균풍속", el.select("ws1M").text()); // 1분 평균 풍속(ws1M)
				awsMap.put("일강수량", el.select("rnDay").text()); // 일강수량(rnDay)
				awsMap.put("aws시간", el.select("awsDt").text()); //aws시간(awsDt)
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
			return awsMap;
		} else {
			return null;
		}
	}

	/**
	 * jsoup 설치 & 추가하기 과거 날씨 정보 가져오기 CheckParameter() 실행후 true일 때만 실행할것.
	 * 
	 * obs 값 목록
	 * "06" => "평균 풍속", "07" => "평균기온", "08" => "최고기온", "10" => "최저기온", "12" => "상대습도",<br> 
	 * "21" => "강수량", "28" => "신적설", "35" => "일조시간", "59" => "운량", "90" => "날씨"<br>
	 * <br>
	 * 
	 * stn 값 목록
	 * "108" => "서울"
	 * @param stn  지역코드
	 * @param year 검색 년도 1960 ~ 현재
	 * @param obs  검색 카테고리
	 * @return String[][];
	 */
	public static String[][] GetPastWeather(String stn, int year, String obs) {
		String[][] wheahers = null;
		String serviceKey = "mxSeDzkLZPTloPLw9fu7PD5G62hSG92WD7NKwFOIs0QnrmQCUHaFtpOTlFzVTAvZ60Efsm22b%2Fhdm9tk66TT7g%3D%3D";

		if (CheckParameter(stn, year, obs)) {
			try {
				StringBuilder urlBuilder = new StringBuilder("htt"); /* URL */
				urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
				urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
						+ URLEncoder.encode("-", "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
				wheahers = new String[33][13];
				final String URL = "http://www.weather.go.kr/weather/climate/past_table.jsp?";
				String parameters = null;

				parameters += "&stn=" + stn;
				parameters += "&yy=" + year;
				parameters += "&obs=" + obs;

				Document doc = Jsoup.connect(URL + parameters).get();

				// Elements names =
				// doc.select("[name=viewform]").select("select").get(0).select("option");
//			
//			System.out.print("{");
//			for(Element el : names){
//				if(!el.val().equals(""))
//					System.out.print("\"" + el.val() + "\",");
//			}
//			System.out.print("}");

				Elements table = doc.select(".table_develop").select("tbody");
				Elements rows = table.get(0).select("tr");

				for (int i = 0; i < 13; i++) {
					if (i != 0)
						wheahers[0][i] = i + "월";
					else
						wheahers[0][i] = "";
				}

				for (int i = 0; i < 32; i++) {
					Elements cols = rows.get(i).select("td");
					for (int j = 0; j < 13; j++) {
						if (cols.get(j).text().equals("")) {
							wheahers[i + 1][j] = null;
						} else {
							wheahers[i + 1][j] = cols.get(j).text();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return wheahers;
		} else {
			return null;
		}
	}

	/**
	 * 검색시 파라미터 유효성 체크 여부 하나라도 틀리면 false.
	 * <br>
	 * obs 값 목록
	 * "06" => "평균 풍속", "07" => "평균기온", "08" => "최고기온", "10" => "최저기온", "12" => "상대습도",<br> 
	 * "21" => "강수량", "28" => "신적설", "35" => "일조시간", "59" => "운량", "90" => "날씨"<br>
	 * <br>
	 * 
	 * stn 값 목록
	 * "108" => "서울"
	 * 
	 * @param stn  지역코드
	 * @param year 검색 년도 1960 ~ 현재
	 * @param obs  검색 카테고리
	 * @return boolean
	 */
	public static boolean CheckParameter(String stn, int year, String obs) {
		boolean result = false;

		String[] obsArr = { "06", "07", "08", "10", "12", "21", "28", "35", "59", "90" };
		String[] stnArr = { "90", "93", "95", "98", "99", "100", "101", "102", "104", "105", "106", "108", "112", "114",
				"115", "119", "121", "127", "129", "130", "131", "133", "135", "136", "137", "138", "140", "143", "146",
				"152", "155", "156", "159", "162", "165", "168", "169", "170", "172", "174", "175", "176", "177", "184",
				"185", "188", "189", "192", "201", "202", "203", "211", "212", "216", "217", "221", "226", "232", "235",
				"236", "238", "243", "244", "245", "247", "248", "251", "252", "253", "254", "255", "256", "257", "258",
				"259", "260", "261", "262", "263", "264", "266", "268", "271", "272", "273", "276", "277", "278", "279",
				"281", "283", "284", "285", "288", "289", "294", "295" };

		Calendar cal = new GregorianCalendar();
		int curYear = cal.get(Calendar.YEAR);

		if (year >= 1960 && year <= curYear) {
			result = true;
		}

		if (result) {
			result = false;
			for (int i = 0; i < obsArr.length; i++) {
				if (obsArr[i].equals(obs)) {
					result = true;
					break;
				}
			}
		}

		if (result) {
			result = false;
			for (int i = 0; i < stnArr.length; i++) {
				if (stnArr[i].equals(stn)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * awsApi의 지역의 유효성 체크 더 많은 지역이 가능하지만 과거정보로 가져올수 있는 지역과 동일 하게 만들기 위해서 지역제한
	 * 
	 * stn 값 목록
	 * "108" => "서울"
	 * 
	 * @param stn
	 * @return boolean 체크 가능한 지역인지 확인
	 */
	public static boolean CheckParameter(String stn) {
		boolean result = false;

		String[] stnArr = { "90", "93", "95", "98", "99", "100", "101", "102", "104", "105", "106", "108", "112", "114",
				"115", "119", "121", "127", "129", "130", "131", "133", "135", "136", "137", "138", "140", "143", "146",
				"152", "155", "156", "159", "162", "165", "168", "169", "170", "172", "174", "175", "176", "177", "184",
				"185", "188", "189", "192", "201", "202", "203", "211", "212", "216", "217", "221", "226", "232", "235",
				"236", "238", "243", "244", "245", "247", "248", "251", "252", "253", "254", "255", "256", "257", "258",
				"259", "260", "261", "262", "263", "264", "266", "268", "271", "272", "273", "276", "277", "278", "279",
				"281", "283", "284", "285", "288", "289", "294", "295" };

		for (int i = 0; i < stnArr.length; i++) {
			if (stnArr[i].equals(stn)) {
				result = true;
				break;
			}
		}

		return result;
	}
}
