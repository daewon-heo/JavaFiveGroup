package develop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Weather {

	/**
	 * 현재 날씨 정보 가져오기
	 * 공공저작물 출처표시
	 * 
	 * @param void
	 * @return void
	 */
	public static void GetCurrentWeather() {
		try {
			final String awsApiUrl = "http://newsky2.kma.go.kr/iros/RetrieveAwsService2/getOneAwsList"; 
			final String serviceKey = "mxSeDzkLZPTloPLw9fu7PD5G62hSG92WD7NKwFOIs0QnrmQCUHaFtpOTlFzVTAvZ60Efsm22b%2Fhdm9tk66TT7g%3D%3D";
			
			Calendar cal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHH00");
			System.out.println(sdf.format(cal.getTime()));
			
			StringBuilder urlBuilder = new StringBuilder(awsApiUrl); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey); /*ServiceKey*/
//			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
			urlBuilder.append("&" + URLEncoder.encode("awsId", "UTF-8") + "=" + URLEncoder.encode("400", "UTF-8")); /* 지점번호 */
			urlBuilder.append("&" + URLEncoder.encode("awsDt", "UTF-8") + "=" + URLEncoder.encode("201808071600", "UTF-8")); /* 연월일시분 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
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
//			System.out.println(sb.toString());
			Document doc = Jsoup.parse(sb.toString());
			Elements el = doc.getAllElements();
			System.out.println(el.select("body"));
			System.out.println(el.select("awsid"));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * jsoup 설치 & 추가하기
	 * 과거 날씨 정보 가져오기 CheckParameter() 실행후 true일 때만 실행할것.
	 * 
	 * @param stn  지역코드
	 * @param year 검색 년도 1960 ~ 현재
	 * @param obs  검색 카테고리
	 * @return String[][];
	 */
	public static String[][] GetPastWeather(String stn, int yyyy, String obs) {
		String[][] wheahers = null;
		try {
			StringBuilder urlBuilder = new StringBuilder("htt"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=mxSeDzkLZPTloPLw9fu7PD5G62hSG92WD7NKwFOIs0QnrmQCUHaFtpOTlFzVTAvZ60Efsm22b%2Fhdm9tk66TT7g%3D%3D"); /*ServiceKey*/
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="
					+ URLEncoder.encode("-", "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
			wheahers = new String[33][13];
			final String URL = "http://www.weather.go.kr/weather/climate/past_table.jsp?";
			String parameters = null;

			parameters += "&stn=" + stn;
			parameters += "&yy=" + yyyy;
			parameters += "&obs=" + obs;

			Document doc = Jsoup.connect(URL + parameters).get();

			//			Elements names = doc.select("[name=viewform]").select("select").get(0).select("option");
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
	}

	/**
	 * 검색시 파라미터 유효성 체크 여부 하나라도 틀리면 false.
	 * 
	 * @param stn  지역코드
	 * @param year 검색 년도 1960 ~ 현재
	 * @param obs  검색 카테고리
	 * @return boolean
	 */
	public static boolean CheckParameter(String stn, int year, String obs) {
		boolean result = false;

		String[] obsArr = {"06", "07", "08", "10", "12", "21", "28", "35", "59", "90"};
		String[] stnArr = {"108","102","98","99","112","119","201","202","203","93","95","101","114","121","211","212","104","115","105","90","100","106","216","217","131","127","135","221","226","177","133","129","232","235","236","238","146","140","243","244","245","247","248","254","172","251","156","165","169","168","170","175","268","252","174","256","260","261","262","259","258","266","136","138","143","176","130","137","271","272","273","277","278","279","281","276","283","159","152","155","255","162","192","284","285","288","289","294","295","253","257","263","264","184","185","189","188"};

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
}
