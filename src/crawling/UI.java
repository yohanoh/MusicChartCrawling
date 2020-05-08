package crawling;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class UI {
	private JFrame mainFrame;
	private JScrollPane melonPane, geniePane;
	private JTable melonTable, genieTable;
	
	
	private ArrayList<MusicInfo> melonList, genieList;
	
	
	public UI(ArrayList<MusicInfo> melonList, ArrayList<MusicInfo> genieList) {
		this.melonList = melonList;
		this.genieList = genieList;
		
		lauchFrame();
	}
	
	public void lauchFrame() {
		mainFrame = new JFrame();
		mainFrame.setLayout(new GridLayout(1, 1, 0, 0));
		mainFrame.setVisible(true);
		mainFrame.setSize(700, 500);
		
		JTabbedPane chartPane = new JTabbedPane(JTabbedPane.TOP);
		mainFrame.add(chartPane);
		
		melonPane = new JScrollPane();
		mainFrame.add(melonPane);
		chartPane.addTab("Melon", melonPane);
		
		geniePane = new JScrollPane();
		mainFrame.add(geniePane);
		chartPane.addTab("Jenie", geniePane);
		
		
		melonTable = new JTable();
		melonTable.setModel(new DefaultTableModel(
				getMusicList(melonList),
				
				new String[] {
					"순위", "곡 제목", "가수"
				}
		));
		
		genieTable = new JTable();
		genieTable.setModel(new DefaultTableModel(
				getMusicList(genieList),
				
				new String[] {
					"순위", "곡 제목", "가수"
				}
		));
		
		melonPane.setColumnHeaderView(melonTable);
		geniePane.setColumnHeaderView(genieTable);
	}
	
	public String[][] getMusicList(ArrayList<MusicInfo> list){
		
		String [][]result = new String[100][3];
		int index = 0;
		
		for(MusicInfo music : list) {
			result[index][0] = Integer.toString(music.getRank());
			result[index][1] = music.getTitle();
			result[index][2] = music.getSinger();
			index++;
		}
		return result;
	}
	
	public static void main(String args[]) {
		ArrayList<MusicInfo> genieList = new ArrayList<MusicInfo>();
		ArrayList<MusicInfo> melonList = new ArrayList<MusicInfo>();
		
		melonList = MusicChart.search("MELON");
		genieList = MusicChart.search("GENIE");
		
		new UI(melonList, genieList);
	}
	
}
