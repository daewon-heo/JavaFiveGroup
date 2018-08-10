package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;

import com.ff.controller.MainController;
import com.ff.controller.PastWeatherController;
import com.ff.controller.SpecificDateController;
import com.ff.controller.TodayStyleController;
import com.ff.model.CommonStatic;

public class MainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2153902511546168300L;
	
	JButton btn1 = null;
	JButton btn2 = null;
	JButton btn3 = null;
	
	JFrame jf = new JFrame();
	
	MainController mc = new MainController();
	
	PastWeatherController view1Controller = null;
	TodayStyleController view2Controller = null;
	SpecificDateController view3Controller = null;
	
	public MainView(){
		super("날씨 봐라");
		init();
	}
	
	public void init() {
		
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
        Image myImg = new ImageIcon("datas/images/main/button1.png").getImage().getScaledInstance(120,35, 0);
        
        // 메뉴 버튼
        initView();
		
		
        
        btn1 = new JButton(new ImageIcon(myImg));
        btn1.setFocusPainted(false); 
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        
        myImg = new ImageIcon("datas/images/main/button2.png").getImage().getScaledInstance(125, 35 , 0);
        btn2 = new JButton(new ImageIcon(myImg));
        btn2.setFocusPainted(false); 
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        
     
        myImg = new ImageIcon("datas/images/main/button3.png").getImage().getScaledInstance(125, 35 , 0);
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
        
        // 메인 아이콘
        Image mainImage = new ImageIcon("datas/images/"+ /*mc.getIconName()+*/"sun.png").getImage().getScaledInstance(300, 300, 0);
        JLabel mainIcon = new JLabel(new ImageIcon(mainImage));
       
       
       
        
        // 글씨 위치 조정
        mainIcon.setBounds(195, 160, 300, 300);
        today.setBounds(185,60,350,60);
        day.setBounds(272,100,400,80);
        nowTem.setBounds(280, 410, 350, 100);
        
        highTem.setBounds(550, 260, 350, 100);
        highTemName.setBounds(540, 220, 350, 100);
        lowTem.setBounds(60, 260, 350, 100);
        lowTemName.setBounds(60, 220, 350, 100);
        
        lowTemName.setLocation(60, 220);
        
        // 버튼 위치 조정
        btn1.setBounds(100, 0, 145, 50);
        btn2.setBounds(250,0,145,50);
        btn3.setBounds(400,0,145,50);
        
        addListener();
        addComponent();
        
        // 프레임에 추가
        add(btn1);
        add(btn2);
        add(btn3);
        add(mainIcon);
        add(today);
        add(lowTemName);
        add(highTemName);
        
        add(day);
        add(nowTem);
        add(highTem);
        add(lowTem);
        add(end);
        
        
    
		setBounds(500, 250, 700, 600);
		setResizable(false);
		setVisible(true);
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
				view2Controller = new TodayStyleController();
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
	      try {
	          final BufferedImage backgroundImage = javax.imageio.ImageIO.read(new File(CommonStatic.BACKGROUND_SKY_IMG));
	          setContentPane(new JPanel(new BorderLayout()) {
	              @Override public void paintComponent(Graphics g) {
	                  g.drawImage(backgroundImage, 0, 0, null);
	              }
	          });
	      } catch (IOException e) {
	          throw new RuntimeException(e);
	      }
	   }
}
