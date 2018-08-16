package com.ff.view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.controller.TodayStyle2Controller;
import com.ff.controller.TodayStyleController;

import com.ff.model.CommonStatic;

public class TodayStyleView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L; //직렬화 
	
	TodayStyleController tsc = TodayStyleController.getInstance();
	
	private static TodayStyleView instance = null;
	
	private JPanel bgPanel = null;
	private JLabel bgLabel = null;
	private JButton moreViewBtn = null;
	
	public static TodayStyleView getInstance() {
		return instance;
	}
	
	public static TodayStyleView getInstance(String fileName) {
		if(instance == null)
			instance = new TodayStyleView(fileName);
		
		if(!instance.isVisible())
			instance.setVisible(true);
		
		return instance;
	}
	
	
	private TodayStyleView(String fileName){
		super("이런 스타일 어때요?");
		initView(fileName);
		
		loadingView();
		
		initComponent();

		addListener();
		setBackImg(fileName);
		
//		setWeatherInfo();
	}
	
	JLabel loadingLabel = null;
	public JPanel loadingPanel = null;
	
	public void loadingView(){
		System.out.println("로딩중 .....");
		ImageIcon imageIcon = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
		loadingPanel = new JPanel();
		loadingLabel = new JLabel(imageIcon);
		
		
		loadingPanel.add(loadingLabel);
		loadingPanel.setLayout(new BorderLayout());
		loadingPanel.add(loadingLabel, "Center");
		loadingPanel.setBounds(25, 550, 150, 150);
		loadingPanel.setOpaque(false);
		add(loadingPanel);
    	
    	try {
			this.setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}
	}
	
	//public void dataView(String[][] datas){}
	
	public void setBackImg(String fileName) {
		bgPanel.remove(bgLabel); // 스타일 더보기 창에서 버튼 클릭시 원래 라벨 지우는것
		bgLabel = new JLabel(new ImageIcon(fileName));
		bgLabel.setLocation(0, 0);
		bgLabel.setSize(450, 780);
		bgPanel.add(bgLabel);
		refresh();
	}

	public void setWeatherInfo(TodayStyleController tsc){
		System.out.println("setWeatherInfo start");
		bgPanel.setLayout(null);
		tsc.weather();
		
		JLabel temper;	
		JLabel region;
		JLabel today;
		JLabel high;
		JLabel low;	
		
        temper = new JLabel();
        temper.setText(tsc.getNowTem()+"℃");
        temper.setForeground(Color.WHITE);
        temper.setFont(new Font("Fixedsys",Font.BOLD, 20));
        temper.setBounds(30, 575, 130, 25);
		this.getContentPane().add(temper);
		
		region = new JLabel();
		region.setText(tsc.getRegion());
		region.setForeground(Color.WHITE);
		region.setFont(new Font("Fixedsys",Font.BOLD, 20));
		region.setBounds(30, 605, 130, 25);	
		this.getContentPane().add(region);
		
		today = new JLabel();
		today.setText(tsc.getToday());
		today.setForeground(Color.WHITE);	
		today.setFont(new Font("Fixedsys",Font.BOLD, 20));
        today.setBounds(30, 640, 180, 25);
		this.getContentPane().add(today);

		high = new JLabel();
		high.setText("Highs: "+tsc.getHigh()+"℃");
		high.setForeground(Color.WHITE);	
		high.setFont(new Font("Fixedsys",Font.BOLD, 20));
		high.setBounds(30, 675, 130, 25);
		this.getContentPane().add(high);

		low = new JLabel();
		low.setText("Lows: "+tsc.getLow()+"℃");
		low.setForeground(Color.WHITE);	
		low.setFont(new Font("Fixedsys",Font.BOLD, 20));
        low.setBounds(30, 705, 130, 25);	
		this.getContentPane().add(low);	
		
		add(bgPanel);
		
		System.out.println("setWeatherInfo end");
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
	
	public void initView(String fileName) {
	

		setBounds(500, 200, 450, 780);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try {
			setIconImage(ImageIO.read(new File(CommonStatic.TODAY_STYLE_ICON)));//아이콘 이미지
		} catch (IOException e) {
			System.out.println("이미지 읽기 실패!");
		}
		
		
	}
	
	
	public void initComponent(){
		
		bgPanel = new JPanel();
		bgLabel = new JLabel();
		add(bgPanel);
		bgPanel.setLayout(null);
		
		moreViewBtn = new JButton(new ImageIcon("datas/images/showmorepicbtn.png"));
		moreViewBtn.setLocation(295, 705);
		moreViewBtn.setSize(130, 35);
		bgPanel.add(moreViewBtn);
	}
	
	public void addListener() {
	
		
		moreViewBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyle2Controller nextView = TodayStyle2Controller.getInstance();
				nextView.viewShow();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
	
	}

	
	public String seasonStyle(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) +1;
		
		String sSeason = null;
		
		switch (iSeason) {
		case 12:
		case 1:
		case 2:
			sSeason = "winter";
			break;
		case 3:
		case 4:
		case 5:
			sSeason = "spring";
			break;
		case 6:
		case 7:
		case 8:
			sSeason = "summer";
			break;
		case 9:
		case 10:
		case 11:
			sSeason = "fall";
			break;
		default:
			break;
		}
		String stylePath = "datas/images/style/" + sSeason +  "/" + String.valueOf((int)(Math.random()*9)+1).substring(0,1) + ".jpg";
		
		System.out.println(stylePath);
		return stylePath;
	}
	
	
 
	
}
