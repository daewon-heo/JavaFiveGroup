package com.ff.view;

import java.awt.Color;
import java.awt.Font;
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

import com.ff.controller.TodayStyleController;

public class TodayStyleView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L;
	
	private TodayStyleController tsc = new TodayStyleController();
	
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
		//setLayout(new GridLayout());
		setResizable(false);
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
		
		JLabel temper;	
		JLabel region;
		JLabel today;
		JLabel high;
		JLabel low;	
		
		// start of 왼쪽 텍스트
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
		high.setText("최고: "+tsc.getHigh()+"℃");
		high.setForeground(Color.WHITE);	
		high.setFont(new Font("Fixedsys",Font.BOLD, 20));
		high.setBounds(30, 675, 130, 25);
		this.getContentPane().add(high);

		low = new JLabel();
		low.setText("최저: "+tsc.getLow()+"℃");
		low.setForeground(Color.WHITE);	
		low.setFont(new Font("Fixedsys",Font.BOLD, 20));
        low.setBounds(30, 705, 130, 25);	
		this.getContentPane().add(low);	
		
		/*JLabel temper = new JLabel();
		temper.setText(tsc.getNowTem()+"℃");
		
		JLabel region = new JLabel();
		region.setText(tsc.getRegion());
		
		JLabel today = new JLabel();
		today.setText(tsc.getToday());
		
		JLabel high = new JLabel();
		high.setText(tsc.getHigh());
		
		JLabel low = new JLabel();
		low.setText(tsc.getLow());
		*/
	
		
//		pan.add(temper);
//		pan.add(region);
//		pan.add(today);
//		pan.add(high);
//		pan.add(low);
	
		
		
		add(pan);
		
		
		
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
