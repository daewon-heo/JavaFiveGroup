package develop;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ff.model.CommonStatic;

public class TestLoading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300781402361621551L;

	JLabel loadingLabel = null;
	
	public TestLoading(){
		super("로딩 화면 테스트");
		loadingView();        	// 로딩 이미지 추가
		initView();				// View 초기화
	
		String[][] str = getPastWeather();	// 과거 날씨 데이터 가져오기

		remove(loadingLabel);
		repaint();				// 다시 그리기
		
		dataView(str);
		validate();			// 다시 그리기
	}
	
	/**
	 * View 초기화
	 */
	public void initView(){
    	setBounds(200, 200, 500, 500);
    	setLayout(new GridLayout());
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 로딩 이미지 추가
	 */
	public void loadingView(){
    	ImageIcon imageIcon = new ImageIcon(CommonStatic.LOADING_IMG_WEATHER);
    	loadingLabel = new JLabel(imageIcon);
    	add(loadingLabel);
	}
	
	/**
	 * 데이터를 받아서 그리는 뷰
	 */
	public void dataView(String[][] pastWeather){
		String[][] editWeather = new String[pastWeather.length-1][pastWeather[0].length];
		
		for (int i = 1; i < pastWeather.length; i++) {
			for (int j = 0; j < pastWeather[0].length; j++) {
				editWeather[i-1][j] = pastWeather[i][j];
			}
		}
		
		JTable wTable = new JTable(editWeather, pastWeather[0]);
		JScrollPane scroll = new JScrollPane(wTable);
		add(scroll);
		pack();
	}
	
	public String[][] getPastWeather(){
		String[][] pastWeather = Weather.GetPastWeather("108", 2018, "08");
		return pastWeather;
	}
	
	public static void main(String[] args) {
		new TestLoading();
	}
}
