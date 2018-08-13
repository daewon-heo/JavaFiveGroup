package com.ff.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import org.hamcrest.core.SubstringMatcher;

import com.ff.controller.SpecificDateController;
import com.ff.model.CommonStatic;
import com.sun.prism.Image;

import develop.Weather;

public class SpecificDateView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;

	JPanel bgPanel = null;
	JTextField textField = null;

	JPanel swapPanel = new JPanel();

	JPanel loadingPanel = null;
	JLabel loadingLabel = null;

	// JPanel resultPanel = null;
	JTextArea area = null;
	Font font = null;

	private static SpecificDateView instance = null;

	public static SpecificDateView getInstance() {
		if (instance == null)
			instance = new SpecificDateView();
		if (!instance.isVisible())
			instance.setVisible(true);
		return instance;
	}

	private SpecificDateView() {
		super("특정 날짜 조회");
		initView();
	}

	public void initView() {
		setResizable(false);
		setLayout(null);

		bgPanel = new JPanel();
		bgPanel.setLayout(null);
		bgPanel.setBackground(Color.white);
		bgPanel.setBounds(0, 0, 450, 600);

		ImageIcon image1 = new ImageIcon("datas/images/nalssibacloud.png"); // 화면
																			// 구름
																			// 이미지
		JLabel label1 = new JLabel(image1);
		label1.setLocation(120, 31);
		label1.setSize(200, 300);

		ImageIcon image2 = new ImageIcon("datas/images/nalssiba.png"); // 화면
																		// 날씨봐라
																		// 이미지
		JLabel label2 = new JLabel(image2);
		label2.setLocation(120, 120);
		label2.setSize(200, 300);

		ImageIcon image3 = new ImageIcon("datas/images/search.png"); // 화면 검색 버튼
																		// 이미지
		JButton searchBtn = new JButton(image3);
		searchBtn.setLocation(275, 320);
		searchBtn.setSize(40, 29);

		ImageIcon image4 = new ImageIcon("datas/images/SunLoading.gif"); // 로딩
																			// 이미지
		JLabel loading = new JLabel(image4);
		loading.setLocation(180, 35);
		loading.setSize(70, 70);

		textField = new JTextField("ex) 20180505");
		textField.setLocation(125, 320);
		textField.setSize(150, 30);
		textField.setFont(font);
		textField.setForeground(Color.blue);
		textField.setHorizontalAlignment(textField.CENTER);

		JTextArea area = new JTextArea();
		area.setLocation(130, 370);
		area.setSize(180, 150);
		area.setEditable(false);
		area.setForeground(Color.blue);

		searchBtn.addActionListener(new ActionListener() { // ActionListener
															// open

			@Override
			public void actionPerformed(ActionEvent e) { // ActionEvent

				String date = textField.getText();
				String result = checkDate(date);

				String str = "";
				if (date.length() == 8) { // 숫자만 걸러내기
					for (int i = 0; i < date.length(); i++) {
						char ch = date.charAt(i);
						if (ch >= '0' && ch <= '9')
							str += ch;
					}
				}

				
				String strYear = str.substring(0, 4); // 년도 추출
				int schYear = Integer.valueOf(strYear); // ex) 2018
				
				if (result.equals("")) {
					loadingView();
					new Thread(SpecificDateController.getInstance()).start();
				} else {
					area.setText(result);
				}

			}
		}); // ActionListener close

		bgPanel.add(label1);
		bgPanel.add(label2);
		bgPanel.add(textField);
		bgPanel.add(searchBtn);
		bgPanel.add(loading);
		add(bgPanel);

		setBounds(300, 300, 450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void loadingView() {
		if (area != null)
			swapPanel.remove(area);
		swapPanel.setLocation(125, 350);
		swapPanel.setSize(180, 200);

		loadingPanel = new JPanel();
		ImageIcon loadingImg = new ImageIcon("datas/images/loading_2.gif");
		loadingLabel = new JLabel(loadingImg);

		loadingPanel.add(loadingLabel);
		swapPanel.add(loadingPanel);

		bgPanel.add(swapPanel);
		refresh();
	}

	public String checkDate(String date) {
		String result = "";
		String str = "";
		if (date.length() == 8) { // 숫자만 걸러내기
			for (int i = 0; i < date.length(); i++) {
				char ch = date.charAt(i);
				if (ch >= '0' && ch <= '9')
					str += ch;
			}
		}

		if (str.length() == 8) { // 걸러져 나온 숫자만 8개라면 실행, 아니면 검색형식 틀렸다

			// ex) 20180510
			String strYear = str.substring(0, 4); // 년도 추출
			int schYear = Integer.valueOf(strYear); // ex) 2018

			String strMonth = str.substring(4, 6); // 월 추출
			int schMonth = Integer.valueOf(strMonth); // ex) 05

			String strDay = str.substring(6, 8); // 일 추출
			int schDay = Integer.valueOf(strDay); // ex) 10

			Calendar cal = new GregorianCalendar(); // 현재 년, 월, 일
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH) + 1;
			int nowDay = cal.get(Calendar.DATE);

			cal.set(schYear, schMonth - 1, 1); // 해당 년,월에 따른 말일 구하기
			int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("해당 월에 말일 : " + lastday);

			if (schYear < 1960 || schYear > nowYear) { // 입력된 연도가 1960 보다 작거나 현재
														// 연도보다 크다면
				String yearfault = "1960년 ~ " + nowYear + "년 조회 가능";
				result = yearfault;
			} else if (schMonth == 0 || schMonth > 12) { // 입력된 월이 0 또는 13이상

				String monthfault = "월을 잘 못 입력하셨습니다.";
				result = monthfault;
			} else if (schYear == nowYear && schMonth > nowMonth) { // 입력된 연도가
																	// 현재 연도와
																	// 같으며
																	// 입력된 월이 현재
																	// 월보다 크면
				String monthfault = "날씨를 조회할 수 없습니다.";
				result = monthfault;
			} else if (schDay == 0 || schDay > lastday) { // 입력된 일이 0 이거나 해당 월에
															// 대한 말일보다 크다면
				String dayfault = "일을 잘 못 입력하셨습니다.";
				result = dayfault;

			} else if (schYear == nowYear && schMonth == nowMonth && schDay >= nowDay) { // 오늘부터~
				String dayfault = "날씨를 조회할 수 없습니다.";
				result = dayfault;

			}

		} else {
			String search = "검색 형식이 잘 못 되었습니다.";
			result = search;

		}
		return result;
	}

	public void dataView(String[][] datas1, String[][] datas2, String[][] datas3, String[][] datas4, String[][] datas5,
			String[][] datas6) {

		if (loadingPanel != null)
			swapPanel.remove(loadingPanel);

		/*
		 * 
		 * testarea 추가 하기
		 */
		font = new Font("맑은 고딕", Font.BOLD, 12);
		area = new JTextArea();
		area.setLocation(130, 370);
		area.setSize(180, 150);
		area.setEditable(false);
		area.setFont(font);
		area.setForeground(Color.blue);

		String strText = textField.getText();

		String str = "";
		if (strText.length() == 8) { // 숫자만 걸러내기
			for (int i = 0; i < strText.length(); i++) {
				char ch = strText.charAt(i);
				if (ch >= '0' && ch <= '9')
					str += ch;
			}
		}

		if (str.length() == 8) { // 걸러져 나온 숫자만 8개라면 실행, 아니면 검색형식 틀렸다
			area.setText("");

			// ex) 20180510
			String strYear = str.substring(0, 4); // 년도 추출
			int schYear = Integer.valueOf(strYear); // ex) 2018

			String strMonth = str.substring(4, 6); // 월 추출
			int schMonth = Integer.valueOf(strMonth); // ex) 05

			String strDay = str.substring(6, 8); // 일 추출
			int schDay = Integer.valueOf(strDay); // ex) 10

			Calendar cal = new GregorianCalendar(); // 현재 년, 월, 일
			int nowYear = cal.get(Calendar.YEAR);
			int nowMonth = cal.get(Calendar.MONTH) + 1;
			int nowDay = cal.get(Calendar.DATE);

			cal.set(schYear, schMonth - 1, 1); // 해당 년,월에 따른 말일 구하기
			int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("해당 월에 말일 : " + lastday);

			if (schYear < 1960 || schYear > nowYear) { // 입력된 연도가 1960 보다 작거나 현재
														// 연도보다 크다면
				area.setText("");
				String yearfault = "1960년 ~ " + nowYear + "년 조회 가능";
				area.append(yearfault);
				System.out.println(yearfault);

			} else if (schMonth == 0 || schMonth > 12) { // 입력된 월이 0 또는 13이상

				area.setText("");
				String monthfault = "월을 잘 못 입력하셨습니다.";
				area.append(monthfault);

			} else if (schYear == nowYear && schMonth > nowMonth) { // 입력된 연도가
																	// 현재 연도와
																	// 같으며
																	// 입력된 월이 현재
																	// 월보다 크면
				area.setText("");
				String monthfault = "날씨를 조회할 수 없습니다.";
				area.append(monthfault);

			} else if (schDay == 0 || schDay > lastday) { // 입력된 일이 0 이거나 해당 월에
															// 대한 말일보다 크다면
				area.setText("");
				String dayfault = "일을 잘 못 입력하셨습니다.";
				area.append(dayfault);

			} else if (schYear == nowYear && schMonth == nowMonth && schDay >= nowDay) { // 오늘부터~
				area.setText("");
				String dayfault = "날씨를 조회할 수 없습니다.";
				area.append(dayfault);

			} else {
				// 서울지역

				SimpleDateFormat sdf = new SimpleDateFormat(" yyyy년 M월 d일 E요일 날씨\n");
				cal.set(schYear, schMonth - 1, schDay);
				String specific = sdf.format(cal.getTime());

				area.setText("");
				area.append(specific);
				area.append("      평균 온도  :\t" + datas1[schDay][schMonth].toString() + " ℃\n");
				area.append("      최고 온도  :\t" + datas2[schDay][schMonth].toString() + " ℃\n");
				area.append("      최저 온도  :\t" + datas3[schDay][schMonth].toString() + " ℃\n");
				area.append("      평균 습도  :\t" + datas4[schDay][schMonth].toString() + " %\n");

				if (datas5[schDay][schMonth] == null) {
					area.append("      강  수  량  :\t0.0 mm\n");
				} else {
					area.append("      강  수  량  :\t" + datas5[schDay][schMonth].toString() + " mm\n");
				}
				if (datas6[schDay][schMonth] == null) {
					area.append("      적  설  량  :\t0.0 cm\n");
				} else {
					area.append("      적  설  량  :\t" + datas6[schDay][schMonth].toString() + " cm\n");
				}

				textField.setText("");

			} // 두번째 if문 close

		} else {
			String search = "검색 형식이 잘 못 되었습니다.";
			area.setText("");
			area.append(search);

		} // 첫 if문 close

		textField.requestFocus();

		swapPanel.add(area);
		refresh();
	}

	public String[][] getData() {
		String[][] datas = SpecificDateController.getInstance().getPastWeather();
		return datas;
	}

	public void refresh() {
		revalidate();
		repaint();
	}
}
