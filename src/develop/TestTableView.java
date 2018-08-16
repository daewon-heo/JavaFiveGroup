package develop;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ff.model.Weather;

public class TestTableView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092901572598871115L;

	public TestTableView(){
		super("테이블 뷰 테스트");
		initView();
		dataView(getData());
		revalidate();
	}
	
	public void initView(){
		setBounds(300, 300, 550, 550);
		setLayout(new GridLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String[][] getData(){
		String[][] pastWeather = Weather.GetPastWeather("108", 2018, "90");
		return pastWeather;
	}
	
	public void dataView(String[][] pastWeather){
		String[] headers = getHeaders(pastWeather);
		String[][] rowDatas = getRowData(pastWeather);
		
		JTable wTable = new JTable(rowDatas, headers);
		JScrollPane scroll = new JScrollPane(wTable);
		add(scroll);
		
	}
	
	public String[] getHeaders(String[][] pastWeather){
		return pastWeather[0];
		
	}
	
	public String[][] getRowData(String[][] pastWeather){
		String[][] rowDatas = new String[pastWeather.length-1][pastWeather[0].length];
		
		for (int i = 1; i < pastWeather.length; i++) {
			for (int j = 0; j < pastWeather[0].length; j++) {
				rowDatas[i-1][j] = pastWeather[i][j];
			}
		}
		return rowDatas;
	}
	
	public static void main(String[] args) {
		new TestTableView();
	}
	
}
