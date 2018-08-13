package com.ff.view;

import java.awt.CardLayout;
import java.awt.Color;
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

import org.hamcrest.core.SubstringMatcher;

import com.ff.controller.SpecificDateController2;
import com.ff.model.CommonStatic;
import com.sun.prism.Image;

import develop.Weather;

public class SpecificDateView2 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114497684814447320L;

	JPanel bgPanel = null;
	JTextField textField = null;
	
	JPanel swapPanel = new JPanel();
	
	JPanel loadingPanel = null;
	JLabel loadingLabel = null;
	
	JPanel resultPanel = null;

	private static SpecificDateView2 instance = null;

	public static SpecificDateView2 getInstance() {
		if (instance == null)
			instance = new SpecificDateView2();
		if(!instance.isVisible())
			instance.setVisible(true);
		return instance;
	}

	private SpecificDateView2() {
		super("특정 날짜 조회");
		initView();
	}

	public void initView() {
		setResizable(false);
		setLayout(null);

		bgPanel = new JPanel();
		bgPanel.setLayout(null);
		bgPanel.setBackground(Color.lightGray);
		bgPanel.setBounds(0, 0, 450, 600);

		ImageIcon image1 = new ImageIcon("datas/images/nalssibacloud.png");
		JLabel label1 = new JLabel(image1);
		label1.setLocation(120, 11);
		label1.setSize(200, 300);

		ImageIcon image2 = new ImageIcon("datas/images/nalssiba.png");
		JLabel label2 = new JLabel(image2);
		label2.setLocation(120, 100);
		label2.setSize(200, 300);

		ImageIcon image3 = new ImageIcon("datas/images/search.png");
		JLabel label3 = new JLabel(image3);
		JButton btn = new JButton(image3);
		btn.setLocation(275, 300);
		btn.setSize(40, 29);
		
		JButton btn2 = new JButton(image3);
		btn2.setLocation(310, 300);
		btn2.setSize(40, 29);

		textField = new JTextField(20);
		textField.setLocation(125, 300);
		textField.setSize(150, 30);
		
		JTextArea area = new JTextArea();
		area.setLocation(130, 370);
		area.setSize(180, 150);
		area.setEditable(false);
		area.setForeground(Color.blue);

		btn.addActionListener(new ActionListener() { // ActionListener open

			@Override
			public void actionPerformed(ActionEvent e) { // ActionEvent
				loadingView();
			}
		}); // ActionListener close
		
		btn2.addActionListener(new ActionListener() { 

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(SpecificDateController2.getInstance()).start();
			}
		}); 
		

		bgPanel.add(label1);
		bgPanel.add(label2);
		bgPanel.add(textField);
		bgPanel.add(btn);
		bgPanel.add(btn2);
		bgPanel.add(label3);

		add(bgPanel);

		setBounds(300, 300, 450, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void loadingView(){
		if(resultPanel != null)
			swapPanel.remove(resultPanel);
		swapPanel.setLocation(125, 350);
		swapPanel.setSize(180, 200);
		
		loadingPanel = new JPanel();
		ImageIcon loadingImg = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
		loadingLabel = new JLabel(loadingImg);
		
		loadingPanel.add(loadingLabel);
		swapPanel.add(loadingPanel);
		
		bgPanel.add(swapPanel);
		refresh();
	}
	
	public void dataView(String[][] datas){
		if(loadingPanel != null)
			swapPanel.remove(loadingPanel);
		
		
		resultPanel = new JPanel();
		resultPanel.setLocation(125, 350);
		resultPanel.setSize(180, 200);
		
		resultPanel.setBackground(Color.RED);
		
		/*
		 * 
		 * testarea 추가 하기
		*/
		
		swapPanel.add(resultPanel);
		refresh();
	}
	
	public String[][] getData(){
		String[][] datas = SpecificDateController2.getInstance().getPastWeather();
		return datas;
	}
	
	public void refresh(){
		revalidate();
		repaint();
	}
}

