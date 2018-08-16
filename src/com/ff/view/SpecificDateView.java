package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import org.hamcrest.core.SubstringMatcher;

import com.ff.controller.SpecificDateController;

public class SpecificDateView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;

	JPanel bgPanel = null;
	JTextField textField = null;

	JPanel swapPanel = new JPanel();

	JPanel loadingPanel = new JPanel();
	ImageIcon loadingImg = new ImageIcon("datas/images/loading_3.gif");
	JLabel loadingLabel = new JLabel(loadingImg);
	JPanel resultPanel = new JPanel();

	JTextArea area = null;
	Font font = null;
	JButton searchBtn = null;

	private static SpecificDateView instance = null;

	public static SpecificDateView getInstance() {
		if (instance == null)
			instance = new SpecificDateView();
		if (!instance.isVisible())
			instance.setVisible(true);
		return instance;
	}

	private SpecificDateView() {
		super("이 날의 날씨가 궁금해!");
		
		initView();
		
		addListener();
	}

	/*
	 * Frame View 설정
	 */
	public void initView() {
		initComponentAdd();
		dataComponentAdd();
		
		setResizable(false);
		setLayout(null);
		setBounds(300, 300, 450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/*
	 * add Component
	 */
	
	public void initComponentAdd(){
		bgPanel = new JPanel();
		bgPanel.setLayout(null);
		bgPanel.setBackground(Color.white);
		bgPanel.setBounds(0, 0, 450, 600);
		
		
		try {
			this.setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));	// 아이콘 이미지
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		JLabel background = new JLabel(new ImageIcon(CommonStatic.BACKGROUND_MAIN_IMG));	// 배경이미지
//		background.setBounds(0, 0, 450, 600);

		ImageIcon image1 = new ImageIcon("datas/images/nalssibacloud.png"); // 화면 구름 이미지
		JLabel label1 = new JLabel(image1);
		label1.setBounds(123, 31, 200, 300);

		ImageIcon image2 = new ImageIcon("datas/images/nalssiba.png"); // 화면 날씨봐라 이미지
		JLabel label2 = new JLabel(image2);
		label2.setBounds(120, 120, 200, 300);

		ImageIcon image3 = new ImageIcon("datas/images/search.png"); // 화면 검색 버튼 이미지
		searchBtn = new JButton(image3);
		searchBtn.setBounds(275, 320, 40, 29);

		ImageIcon image4 = new ImageIcon("datas/images/SunLoading.gif"); // 로딩 이미지
		JLabel loading = new JLabel(image4);
		loading.setBounds(180, 35, 70, 70);

		textField = new JTextField();
		textField.setBounds(125, 320, 150, 30);
		textField.setFont(font);
		textField.setForeground(Color.blue);
		textField.setHorizontalAlignment(textField.CENTER);
		
		
		textField.setLayout(new FlowLayout());	// 플레이스 홀더 
		JLabel placeholder = new JLabel("ex) 19900906 ");
		placeholder.setFont(font);
		placeholder.setForeground(Color.gray);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()!=KeyEvent.VK_BACK_SPACE) {
					placeholder.setVisible(false);
				} else {
					if(textField.getText().equals("")) {
						placeholder.setVisible(true);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == '\n'){
					System.out.println("엔터");
					String result = checkDate(textField.getText());
					resultTextInput(result);
				}
			}
		});
		
		textField.add(placeholder);
		bgPanel.add(label1);
		bgPanel.add(label2);
		bgPanel.add(textField);
		bgPanel.add(searchBtn);
		bgPanel.add(loading);
//		bgPanel.add(background);
		swapPanel.setBounds(125, 350, 180, 200);
		bgPanel.add(swapPanel);
		add(bgPanel);
	}

	public void addListener(){
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = checkDate(textField.getText());
				
				resultTextInput(result);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
	}

	public void loadingView() {
		if (resultPanel.isVisible()){
			resultPanel.setVisible(false);
		}
		
		loadingPanel.setBounds(125, 350, 180, 200);

		loadingPanel.add(loadingLabel);
		loadingPanel.setVisible(true);
		
		swapPanel.add(loadingPanel);

		//		refresh();
	}

	/*
	 * textbox 입력값을 받아 결과 값을 반환해준다.
	 */
	public String checkDate(String date) {
		System.out.println("check data parameter : " + date);
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

			if (schYear < 1960 || schYear > nowYear) { // 입력된 연도가 1960 보다 작거나 현재 연도보다 크다면
				String yearfault = "\n1960년 ~ " + nowYear + "년 조회 가능";
				result = yearfault;
			} else if (schMonth == 0 || schMonth > 12) { // 입력된 월이 0 또는 13이상
				String monthfault = "\n월을 잘 못 입력하셨습니다.";
				result = monthfault;
			} else if (schYear == nowYear && schMonth > nowMonth) { // 입력된 연도가 현재 연도와 같으며 입력된 월이 현재 월보다 크면
				String monthfault = "\n날씨를 조회할 수 없습니다.";
				result = monthfault;
			} else if (schDay == 0 || schDay > lastday) { // 입력된 일이 0 이거나 해당 월에
															// 대한 말일보다 크다면
				String dayfault = "\n일을 잘 못 입력하셨습니다.";
				result = dayfault;
			} else if (schYear == nowYear && schMonth == nowMonth && schDay >= nowDay) { // 오늘부터~
				String dayfault = "\n날씨를 조회할 수 없습니다.";
				result = dayfault;
			}
		} else {
			result = "\n검색 형식이 잘 못 되었습니다.";
		}
		
		System.out.println("check date result : " + result);
		return result;
	}

	public void dataComponentAdd(){
		if (loadingPanel.isVisible()){
			loadingPanel.setVisible(false);
		}
		
		loadingPanel.setVisible(true);
		loadingPanel.setBackground(Color.white);

		loadingLabel.setBackground(Color.white);
		
		swapPanel.setVisible(true);
		swapPanel.setBackground(Color.white);
		resultPanel.setLayout(new BorderLayout());
		swapPanel.add(resultPanel);
		
		font = new Font("맑은 고딕", Font.BOLD, 12);
		area = new JTextArea();
		
		area.setSize(180, 150);
		area.setBounds(130, 370, 180, 150);
		area.setEditable(false);
		area.setFont(font);
		area.setForeground(Color.blue);
		area.setBackground(Color.white);
		area.setVisible(true);

		resultPanel.add(area);
	}
	public void dataView(String[] datas, String[][] params) {
		if (loadingPanel.isVisible()){
			loadingPanel.setVisible(false);
		}
		resultPanel.setVisible(true);
		
		String date = textField.getText().trim();
		
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		int day = Integer.parseInt(date.substring(6, 8));
		
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY년 M월 d일 E요일 날씨");
		cal.set(year, month, day);
		
		
		String data = "\n" + sdf.format(cal.getTime()) + "\n"; 
		for (int i = 0; i < datas.length; i++) {
			if(datas[i] == null) {
				if (i == 4) {
					data += params[i][1] + " : 0.0 mm  \n";
				} else {
					data += params[i][1] + " : 0.0 cm  \n";
				}
				
			}else{
				if(i <= 2) {
					data += params[i][1] + " : " + datas[i] + " ℃  \n";
				} else if (i == 3) {
					data += params[i][1] + " : " + datas[i] + " %  \n";
				} else if (i == 4) {
					data += params[i][1] + " : " + datas[i] + " mm  \n";
				} else {
					data += params[i][1] + " : " + datas[i] + " cm  \n";
				}
				
			}
		}
		textInput(data);
		textField.requestFocus();
	}
	
	public void resultTextInput(String text){
		
		if (text.equals("")) {
			int year = Integer.parseInt(textField.getText().substring(0,4));
			loadingView();
			
			new Thread(SpecificDateController.getInstance(textField.getText())).start();
		} else {
			textInput(text);
		}
		refresh();
	}
	
	public void textInput(String text){
		area.setText("");
		area.append(text + "\n");
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
