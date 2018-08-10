package com.ff.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.controller.TodayStyleController2;

public class TodayStyleView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L;
	
	public TodayStyleView(String fileName){
		super("이런 스타일 어때요?");
		initView(fileName);
		System.out.println(fileName);
	}
	
	public void dataView(String[][] datas){
//		JLabel label = new JLabel(datas[3][3]);
//		add(label);
	}
	
	public void initView(String fileName) {
		
		setBounds(500, 200, 500, 500);
		setSize(450, 800);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
//		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pan = new JPanel();
		JLabel lb = new JLabel(new ImageIcon(fileName));
		lb.setLocation(0, 0);
		lb.setSize(450, 800);
//	
		pan.add(lb);
		add(pan);
		
		pan.setLayout(null);
		
		JButton btn = new JButton(new ImageIcon("datas/images/showmorepicbtn.png"));
		btn.setLocation(290, 710);
		btn.setSize(130, 35);
	
		pan.add(btn);
		
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new TodayStyle2View();
				
			}
			
		});
		
		
		
		try {
			setIconImage(ImageIO.read(new File("datas/images/rainbow.png")));
			

		} catch (IOException e) {
		
			System.out.println("이미지 읽기 실패!");
		}
		
	}
	
}
