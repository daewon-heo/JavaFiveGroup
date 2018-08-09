package develop;

import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ff.model.CommonStatic;

public class TestLoading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300781402361621551L;

	JLabel loadingLabel = null;
	
	
	public TestLoading(){
		loadingView();        	// 로딩 이미지 추가
		initView();				// View 초기화
	
		String[][] str = getPastWeather();	// 과거 날씨 데이터 가져오기

		remove(loadingLabel);
		repaint();
		
		dataView(str);
		revalidate();
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
		JPanel pan = new JPanel();
		JLabel label = null;
		for (int i = 0; i < pastWeather.length; i++) {
			for (int j = 0; j < pastWeather[0].length; j++) {
				if(pastWeather[i][j] != null)
					pan.add(new Label(pastWeather[i][j].toString()));
				else
					pan.add(new Label("null"));
			}
		}
		add(pan);
	}
	
	public String[][] getPastWeather(){
		String[][] pastWeather = Weather.GetPastWeather("108", 2018, "08");
		return pastWeather;
	}
	
	public static void main(String[] args) {
		new TestLoading();
	}
}
