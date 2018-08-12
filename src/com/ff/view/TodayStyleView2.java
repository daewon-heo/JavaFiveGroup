package com.ff.view;

import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.internal.runners.statements.InvokeMethod;

import com.ff.controller.TodayStyle2Controller2;
import com.ff.model.CommonStatic;

public class TodayStyleView2 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 181983372302562901L;
	
	private static TodayStyleView2 instance = null;

	// component ...
	private JButton nextViewBtn = null;
	private JButton testViewBtn = null;
	private JPanel bgPanel = null;
	private JLabel bgLabel = null;
	
	public static TodayStyleView2 getInstance() {
		return instance;
	}
	
	public static TodayStyleView2 getInstance(String fileName) {
		if(instance == null)
			instance = new TodayStyleView2(fileName);
		
		if(!instance.isVisible())
			instance.setVisible(true);
		
		return instance;
	}
	
	private TodayStyleView2(String fileName){
		super("이런 스타일 어때요?");
		initView(fileName);
		initComponent();
		addListener();
		setBackImg(fileName);
	}
	
	public void dataView(String[][] datas){
	}
	
	public void initComponent() {
		bgPanel = new JPanel();
		bgLabel = new JLabel();
		add(bgPanel);
		bgPanel.setLayout(null);
		
		nextViewBtn = new JButton(new ImageIcon("datas/images/showmorepicbtn.png"));
		testViewBtn = new JButton("배경이미지 변경");
		
		nextViewBtn.setLocation(50, 700);
		nextViewBtn.setSize(130, 35);
		
		testViewBtn.setLocation(180, 700);
		testViewBtn.setSize(130, 35);
		bgPanel.add(nextViewBtn);
		bgPanel.add(testViewBtn);
	}
	
	public void addListener() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				instance = null;
			}
		});
		
		nextViewBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TodayStyle2Controller2 nextView = TodayStyle2Controller2.getInstance();
				nextView.viewShow();
			}
		});
		
		testViewBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("배경 이미지 랜덤 변경");
				bgPanel.remove(bgLabel);
				setBackImg(seasonStyle());
			}
		});
	}
	
	public void setBackImg(String fileName) {
		bgPanel.remove(bgLabel);
		System.out.println("bgimg변경");
		bgLabel = new JLabel(new ImageIcon(fileName));
		bgLabel.setLocation(0, 0);
		bgLabel.setSize(450, 800);
		bgPanel.add(bgLabel);
		refresh();
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
	
	public void initView(String fileName) {
		setBounds(500, 200, 500, 500);
		setSize(450, 800);
		setLocationRelativeTo(null);
		setLayout(new GridLayout());
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			setIconImage(ImageIO.read(new File(CommonStatic.TODAY_STYLE_ICON)));
		} catch (IOException e) {
			System.out.println("이미지 읽기 실패!");
		}
		
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
