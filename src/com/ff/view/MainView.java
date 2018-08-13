package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.controller.MainController;
import com.ff.controller.PastWeatherController;
import com.ff.controller.SpecificDateController;
import com.ff.controller.TodayStyleController;
import com.ff.model.CommonStatic;

import develop.Weather;

public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153902511546168300L;
	
	JButton btn1 = null;
	JButton btn2 = null;
	JButton btn3 = null;
	
	JFrame jf = new JFrame();
	JLabel loadingLabel = null;
	MainController mc = new MainController();
	
	PastWeatherController view1Controller = null;
	TodayStyleController view2Controller = null;
	SpecificDateController view3Controller = null;
	
	public MainView(){
		super("날씨 봐라");
		//loadingView();
		
		init();
		
		
		String[][] str = getPastWeather();	// 과거 날씨 데이터 가져오기
	}
	
	
	public String[][] getPastWeather(){
		String[][] pastWeather = Weather.GetPastWeather("108", 2018, "08");
		return pastWeather;
	}
	
	public void loadingView(){
		backGrounImg1();
		ImageIcon imageIcon = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
		loadingLabel = new JLabel(imageIcon);
    	add(loadingLabel);
    	
    	try {
			this.setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}
    	setBounds(500, 250, 700, 600);
    	setVisible(true);
	}
	

	public void init() {
		//backGrounImg1();
		//loadingView();
		
		JPanel ldpan = new JPanel();
		backGrounImg1();
		ImageIcon imageIcon = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
		loadingLabel = new JLabel(imageIcon);
    	add(loadingLabel);

    	try {
			this.setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}
    	
    	setBounds(500, 250, 700, 600);
    	setVisible(true);
    	
    	mc.getDatas();

		setLayout(null);
		backGrounImg1();
		
		
		ImageIcon icon = new ImageIcon(CommonStatic.BACKGROUND_SKY_IMG);
        
        
		try {
			this.setIconImage(ImageIO.read(new File("datas/images/S_cloudy.png")));
		} catch (IOException e1) {
			System.out.println("이미지파일 오류 발생");
		}

		
		JLabel end = new JLabel();
		
		Image backg = new ImageIcon(CommonStatic.BACKGROUND_SKY_IMG).getImage().getScaledInstance(700, 600, 50);
        Image myImg = new ImageIcon("datas/images/main/button1.png").getImage();
        
        // 메뉴 버튼
        initView();
		
	
        btn1 = new JButton(new ImageIcon(myImg));
        btn1.setFocusPainted(false); 
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);

        
        myImg = new ImageIcon("datas/images/main/button2.png").getImage();
        btn2 = new JButton(new ImageIcon(myImg));
        btn2.setFocusPainted(false); 
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);

     
        myImg = new ImageIcon("datas/images/main/button3.png").getImage();
        btn3 = new JButton(new ImageIcon(myImg));
        btn3.setFocusPainted(false); 
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);

        
        // 날짜
        JLabel today = new JLabel();
        JLabel day = new JLabel();
        today.setText(mc.getToday());
        today.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        day.setText(mc.getDay());
        day.setFont(new Font("맑은 고딕", Font.BOLD, 50));
        System.out.println(mc.getToday());
        
        // 현재 기온
        JLabel nowTem = new JLabel();
        nowTem.setText(mc.getNowTem()+"℃");
        nowTem.setFont(new Font("맑은 고딕", Font.BOLD, 60));
        
        // 최저 기온
        JLabel highTemName = new JLabel("최고 온도");
        highTemName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        JLabel highTem = new JLabel();
        highTem.setText(mc.getHigh()+"℃");
        highTem.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        
        // 최고 기온
        JLabel lowTemName = new JLabel("최저 온도");
        lowTemName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        JLabel lowTem = new JLabel();
        lowTem.setText(mc.getLow()+ "℃");
        lowTem.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        
        // 상대 습도
        JLabel humidityName = new JLabel("습도");
        humidityName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        JLabel humidity = new JLabel();
        humidity.setText(mc.getHumidity()+"%");
        humidity.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        
        // 메인 아이콘
        Image mainImage = new ImageIcon("datas/images/"+ mc.getIconName()+".png").getImage().getScaledInstance(300, 300, 0);
        JLabel mainIcon = new JLabel(new ImageIcon(mainImage));
       
        // 메뉴 아이콘
        Image menu1Image = new ImageIcon("datas/images/main/analysis.png").getImage().getScaledInstance(40, 40, 0);
        JLabel menu1Icon = new JLabel(new ImageIcon(menu1Image));
        Image menu2Image = new ImageIcon("datas/images/main/tie.png").getImage().getScaledInstance(40, 40, 0);
        JLabel menu2Icon = new JLabel(new ImageIcon(menu2Image));
        Image menu3Image = new ImageIcon("datas/images/main/appointment.png").getImage().getScaledInstance(40, 40, 0);
        JLabel menu3Icon = new JLabel(new ImageIcon(menu3Image));
        
        // 최고 최저 온도
        Image highImage = new ImageIcon("datas/images/main/high.png").getImage().getScaledInstance(80, 80, 0);
        JLabel highIcon = new JLabel(new ImageIcon(highImage));
        Image lowImage = new ImageIcon("datas/images/main/low.png").getImage().getScaledInstance(80, 80, 0);
        JLabel lowIcon = new JLabel(new ImageIcon(lowImage));
       
        
        // 글씨 위치 조정
        mainIcon.setBounds(195, 160, 300, 300);
        today.setBounds(185,60,350,60);
        day.setBounds(272,100,400,80);
        nowTem.setBounds(280, 410, 350, 100);
        
        highTem.setBounds(550, 260, 350, 100);
        highTemName.setBounds(550, 220, 350, 100);
        highIcon.setBounds( 465 ,250 , 80, 80);
        lowTem.setBounds(90, 260, 350, 100);
        lowTemName.setBounds(130, 220, 350, 100);
        lowIcon.setBounds( 2, 250 , 80, 80);
        
        humidityName.setBounds(600,400,100,100);
        humidity.setBounds(600,500,100,100);
        
        
        lowTemName.setLocation(90, 220);
        
        // 버튼 위치 조정
        btn1.setBounds(70, 0, 170, 50);
        btn2.setBounds(295,0,160,50);
        btn3.setBounds(510,0,170,50);
        menu1Icon.setBounds(30, 5, 40, 40);
        menu2Icon.setBounds(255, 5, 40, 40);
        menu3Icon.setBounds(470, 5, 40, 40);
        
        addListener();
        addComponent();
        
        // 프레임에 추가
        add(btn1);
        add(btn2);
        add(btn3);
        add(menu1Icon);
        add(menu2Icon);
        add(menu3Icon);
        
        add(mainIcon);
        add(today);
        add(lowTemName);
        add(highTemName);
        

        
        add(day);
        add(highIcon);
        add(lowIcon);
        add(nowTem);
        add(highTem);
        add(lowTem);
        
//        add(humidityName);
//        add(humidity);
        add(end);
        
        

		//setBounds(500, 250, 700, 600);
		setResizable(false);
		//setVisible(true);
		
		repaint();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initView() {
		
//		jf.setBounds(200, 200, 300, 300);
//		jf.setLayout(new GridLayout());

		btn1 = new JButton("화면1");
		btn2 = new JButton("화면2");
		btn3 = new JButton("화면3");
		
//		jf.setResizable(false);
//		jf.setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void addListener() {
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				view1Controller = new PastWeatherController();
				view1Controller.viewShow();
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view2Controller =  TodayStyleController.getInstance();
				view2Controller.viewShow();
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view3Controller = new SpecificDateController();
				view3Controller.viewShow();
			}
		});
		
	}
	
	public void addComponent() {
		jf.add(btn1);
		jf.add(btn2);
		jf.add(btn3);
	}
	
	  public void backGrounImg1(){
		 

		  Image snow = new ImageIcon("datas/images/main/snow.png").getImage().getScaledInstance(700, 600, 0);
		  
	    	  BufferedImage backgroundImage;
			  try {
		    	  if(mc.getIconName().equals("snow")){
		    		 backgroundImage = javax.imageio.ImageIO.read(new File("datas/images/main/snow.jpg"));
		    	  }else if(mc.getIconName().equals("rain")){
		    		  backgroundImage = javax.imageio.ImageIO.read(new File("datas/images/main/rain.jpg"));
		    	  }else{
		    		  backgroundImage = javax.imageio.ImageIO.read(new File(CommonStatic.BACKGROUND_SKY_IMG));
		    	  }
	    	  
	          setContentPane(new JPanel(new BorderLayout()) {
	              @Override public void paintComponent(Graphics g) {
	                  g.drawImage(backgroundImage, 0, 0, null);
	              }
	          });
	      } catch (IOException e) {
	          throw new RuntimeException(e);
	      }finally{
	  
	      }
	   }
}
