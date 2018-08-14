package com.ff.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.controller.PastWeatherController2;
import com.ff.model.CommonStatic;
import com.sun.xml.internal.ws.api.server.Container;




public class PastWeatherView2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581181417128426852L;
	
	private static PastWeatherView2 instance = null;
	
	private JPanel pastWeatherPanel = null;		// pan1
	private JPanel currWeatherPanel = null;		// pan2
	private JPanel compWeatherPanel = null;		// pan3
	
	private JPanel swapPanel = null;		// 로딩&데이터 화면을 변경하는 패널
	private JPanel loadingPanel = null;		// 로딩 화면
	private JPanel resultPanel = null;		// 데이터 화면
	
	public static PastWeatherView2 getInstance(String iconAdress) {
		if(instance == null)
			instance = new PastWeatherView2(iconAdress);
		return instance;
	}
	
	private PastWeatherView2(String iconAdress){
		super("작년의 오늘 날씨와 현재 날씨");
		
		initView(iconAdress);
//		lodingView();
	}
	
	public void lodingView(JPanel panel){
		/*
		 * 로딩뷰 이미지 넣기
		 */
//		Container str = panel.getcom
//		if(panel.getComponents()[] getresultPanel != null)
//			panel.swapPanel.remove(resultPanel);
//		
		
		
	}
	
	public void initView(String iconAdress) {
		GridBagLayout gridBag = new GridBagLayout();
		setLayout(gridBag);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.BOTH;
		constraint.weightx = 1.0;
		constraint.weighty = 1.0;
		
		pastWeatherPanel = new JPanel();
		currWeatherPanel = new JPanel();
		compWeatherPanel = new JPanel();
		
		pastWeatherPanel.setLayout(null);
		currWeatherPanel.setLayout(null);
		compWeatherPanel.setLayout(null);
		
		/* panel1 */
		pastWeatherPanel.setBackground(Color.red);
		gridBag.setConstraints(pastWeatherPanel, constraint);
		JLabel label1 = new JLabel("패널1");
		label1.setBounds(50, 50, 100, 100);
		pastWeatherPanel.add(label1);
		
		/* panel2 */
		currWeatherPanel.setBackground(Color.yellow);
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		gridBag.setConstraints(currWeatherPanel, constraint);
		JLabel label2 = new JLabel("패널2");
		label2.setBounds(50, 50, 100, 100);
		currWeatherPanel.add(label2);
		
		/* panel3 */
		compWeatherPanel.setBackground(Color.PINK);
		constraint.weighty = 0.07;
		gridBag.setConstraints(compWeatherPanel, constraint);
		JLabel label3 = new JLabel("패널3");
		label3.setBounds(0, 0, 100, 100);
		compWeatherPanel.add(label3);
		
		add(pastWeatherPanel);
		add(currWeatherPanel);
		add(compWeatherPanel);
		
		setBounds(300, 300, 716, 620);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void dataView(){
		JLabel compareLb = new JLabel(PastWeatherController2.getInstance().compareTemperature());
		compareLb.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		compareLb.setFont(compareLb.getFont().deriveFont(20.0f));
		compWeatherPanel.add(compareLb);
	}
}
