package develop;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Weather {

// 지역
//	<select name="stn" id="observation_select1" class="wid85">
//    <option value="108" selected="selected">서울(유)</option>
//    <option value="102">백령도(유)</option>
//    <option value="98">동두천(무)</option>
//    <option value="99">파주(무)</option>
//    <option value="112">인천(유)</option>
//    <option value="119">수원(유)</option>
//    <option value="201">강화(무)</option>
//    <option value="202">양평(무)</option>
//    <option value="203">이천(무)</option>
//    <option value="93">북춘천(유)</option>
//    <option value="95">철원(무)</option>
//	<option value="101">춘천(무)</option>
//    <option value="114">원주(무)</option>
//    <option value="121">영월(무)</option>
//    <option value="211">인제(무)</option>
//    <option value="212">홍천(무)</option>
//    <option value="104">북강릉(유)</option>
//    <option value="115">울릉도(유)</option>
//    <option value="105">강릉(무)</option>
//    <option value="90">속초(무)</option>
//    <option value="100">대관령(무)</option>
//    <option value="106">동해(무)</option>
//    <option value="216">태백(무)</option>
//    <option value="217">정선군(무)</option>
//    <option value="131">청주(유)</option>
//    <option value="127">충주(무)</option>
//    <option value="135">추풍령(무)</option>
//    <option value="221">제천(무)</option>
//    <option value="226">보은(무)</option>
//	<option value="177">홍성(유)</option>                    
//    <option value="133">대전(유)</option>
//	<option value="129">서산(무)</option>
//    <option value="232">천안(무)</option>
//    <option value="235">보령(무)</option>
//    <option value="236">부여(무)</option>
//    <option value="238">금산(무)</option>
//    <option value="146">전주(유)</option>
//    <option value="140">군산(무)</option>
//    <option value="243">부안(무)</option>
//    <option value="244">임실(무)</option>
//    <option value="245">정읍(무)</option>
//    <option value="247">남원(무)</option>
//    <option value="248">장수(무)</option>
//    <option value="254">순창(무)</option>
//    <option value="172">고창(무)</option>
//    <option value="251">고창(구)</option>
//    <option value="156">광주(유)</option>
//    <option value="165">목포(유)</option>
//    <option value="169">흑산도(유)</option>
//    <option value="168">여수(유)</option>
//    <option value="170">완도(무)</option>
//    <option value="175">진도(첨찰산)(무)</option>
//    <option value="268">진도군(무)</option>
//    <option value="252">영광(무)</option>
//    <option value="174">순천(무)</option>
//    <option value="256">순천(구)</option>
//    <option value="260">장흥(무)</option>
//    <option value="261">해남(무)</option>
//    <option value="262">고흥(무)</option>
//    <option value="259">강진군(무)</option>
//    <option value="258">보성군(무)</option>
//    <option value="266">광양(무)</option>
//    <option value="136">안동(유)</option>
//    <option value="138">포항(유)</option>
//    <option value="143">대구(유)</option>
//    <option value="176">대구(구)</option>
//    <option value="130">울진(무)</option>
//    <option value="137">상주(무)</option>
//    <option value="271">봉화(무)</option>
//    <option value="272">영주(무)</option>
//    <option value="273">문경(무)</option>
//    <option value="277">영덕(무)</option>
//    <option value="278">의성(무)</option>
//    <option value="279">구미(무)</option>
//    <option value="281">영천(무)</option>
//    <option value="276">청송군(무)</option>
//    <option value="283">경주(무)</option>
//    <option value="159">부산(유)</option>
//    <option value="152">울산(유)</option>
//    <option value="155">창원(유)</option>
//    <option value="255">북창원(무)</option>
//    <option value="162">통영(무)</option>
//    <option value="192">진주(무)</option>
//    <option value="284">거창(무)</option>
//    <option value="285">합천(무)</option>
//    <option value="288">밀양(무)</option>
//    <option value="289">산청(무)</option>
//    <option value="294">거제(무)</option>
//    <option value="295">남해(무)</option>
//    <option value="253">김해시(무)</option>
//    <option value="257">양산(무)</option>
//    <option value="263">의령군(무)</option>
//    <option value="264">함양군(무)</option>
//    <option value="184">제주(유)</option>
//    <option value="185">고산(무)</option>
//    <option value="189">서귀포(무)</option>
//    <option value="188">성산(무)</option>
	
	// table_develop
//	<select name="obs" id="observation_select3" class="wid85">

	/**
	 * CheckParameter() 실행후 true일 때만 실행할것.
	 * 
	 * @param stn 지역코드
	 * @param year 검색 년도 1960 ~ 현재 
	 * @param obs 검색 카테고리
	 * @return String[][];
	 */
	public static String[][] GetTable(String stn, int yyyy, String obs) {
		String[][] wheahers = null;
		try {
			wheahers = new String[33][13];
			final String URL = "http://www.weather.go.kr/weather/climate/past_table.jsp?";
			String parameters = null;
			
			parameters += "&stn="+stn;
			parameters += "&yy="+yyyy;
			parameters += "&obs="+obs;
			
			Document doc = Jsoup.connect(URL+parameters).get();
			Elements table = doc.select(".table_develop").select("tbody");
			Elements rows = table.get(0).select("tr");
			
			for (int i = 0; i < 13; i++) {
				if(i != 0)
					wheahers[0][i] = i + "월";
				else
					wheahers[0][i] = "";
			}

			for (int i = 0; i < 32; i++) {
				Elements cols = rows.get(i).select("td");
				for (int j = 0; j < 13; j++) {
					if(cols.get(j).text().equals("")) {
						wheahers[i+1][j] = null;
					}else {
						wheahers[i+1][j] = cols.get(j).text();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wheahers;
	}

	/**
	 * 검색시 파라미터 유효성 체크 여부
	 * 하나라도 틀리면 false.
	 * 
	 * @param stn 지역코드
	 * @param year 검색 년도 1960 ~ 현재 
	 * @param obs 검색 카테고리
	 * @return boolean
	 */
	public static boolean CheckParameter(String stn, int year, String obs) {
		boolean result = false;
		
		String[] obsArr = {"06","07","08", "10", "12", "21", "28", "35", "59", "90"};
		String[] strArr = {"108","102"};
		
		Calendar cal = new GregorianCalendar();
		int curYear = cal.get(Calendar.YEAR);
		
		if(year >= 1960 && year <= curYear) {
			result = true;
		}
		
		if(result) {
			result = false;
			for (int i = 0; i < obsArr.length; i++) {
				if(obsArr[i].equals(obs)) {
					result = true;
					break;
				}
			}
		}
		
		if(result) {
			result = false;
			for (int i = 0; i < strArr.length; i++) {
				if(strArr[i].equals(stn)) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
}
