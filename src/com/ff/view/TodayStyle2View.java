package com.ff.view;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ff.model.CommonStatic;

public class TodayStyle2View extends JFrame {
	private static final long serialVersionUID = 781983372302967463L;
	
	private static TodayStyle2View instance = null;
	
	
	private JButton[] btnArr = null;
	private Image[] picArr = null;
	private String[] pathArr = null;
	
	public static TodayStyle2View getInstance() {
		if(instance == null)
			instance = new TodayStyle2View();
		
		if(!instance.isVisible())
			instance.setVisible(true);
		
		return instance;
	}
	
	private TodayStyle2View(){
		super("스타일 더보기");
		subView();
		
	}
	
	public void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
		
	}
	
	public void subView(){
		setLayout(null);
		setBounds(970, 200, 460, 812);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(null);
		setContentPane(panel);
		nineImg(getSeason(), panel);
		setVisible(true);

		try {
			
			setIconImage(ImageIO.read(new File(CommonStatic.TODAY_STYLE_ICON)));

		} catch (IOException e) {
		
			System.out.println("이미지 읽기 실패!");
		}
		
		
	}
	
	
	
	public String getSeason(){
		Calendar cal = new GregorianCalendar();
		int iSeason = cal.get(Calendar.MONTH) +1;
		System.out.println("현재 월 : " + iSeason);
		
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
		return sSeason;
	}
	
public void nineImg(String season, JPanel panel){
		
		String path = "datas/images/style/" + season + "/";
		
		btnArr = new JButton[9];
		picArr = new Image[9];
		pathArr = new String[9];
		
		for (int i = 0; i < pathArr.length; i++) {
			pathArr[i] = path + (i+1) + ".jpg";
		}
		
		for (int i = 0; i < btnArr.length; i++) {
			btnArr[i] = new JButton();
		}
		
		try {
			for (int i = 0; i < picArr.length; i++) {
				picArr[i] = new ImageIcon(pathArr[i]).getImage().getScaledInstance(150, 260, 0);
				
			}

		    for (int i = 0; i < btnArr.length; i++) {
				btnArr[i].setIcon(new ImageIcon(picArr[i]));
				
				btnArr[0].setBounds(1, 1, 150, 260);
				btnArr[1].setBounds(152, 1, 150, 260);
				btnArr[2].setBounds(303, 1, 150, 260);
				btnArr[3].setBounds(1, 262, 150, 260);
				btnArr[4].setBounds(152, 262, 150, 260);
				btnArr[5].setBounds(303, 262, 150, 260);
				btnArr[6].setBounds(1, 523, 150, 260);
				btnArr[7].setBounds(152, 523, 150, 260);
				btnArr[8].setBounds(303, 523, 150, 260);
				
			}
		    
		    addBtnActionListener();

		    for (int i = 0; i < btnArr.length; i++) {
				panel.add(btnArr[i]);
			}
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public void addBtnActionListener() {
		btnArr[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[0]);
			}
		});
		
		btnArr[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance();
				if(view1 != null)
					view1.setBackImg(pathArr[1]);
			}
		});
		
		btnArr[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[2]);
			}
		});
		
		btnArr[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[3]);
			}
		});
		
		btnArr[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[4]);
			}
		});
		
		btnArr[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[5]);
			}
		});
		
		btnArr[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[6]);
			}
		});
		
		btnArr[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[7]);
			}
		});
		
		btnArr[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyleView view1 = TodayStyleView.getInstance(); 
				if(view1 != null)
					view1.setBackImg(pathArr[8]);
			}
		});
	}
}
