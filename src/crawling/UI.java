package crawling;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


class SearchThread implements Runnable{
	private ArrayList<MusicInfo> genieList;
	private ArrayList<MusicInfo> melonList;
	private JScrollPane melonPane, geniePane; 
	private JTable melonTable, genieTable;
	private JButton startBtn;
	
	public SearchThread(JTable melonTable, JTable genieTable, JScrollPane melonPane, JScrollPane geniePane, JButton startBtn) {
		this.melonTable = melonTable;
		this.genieTable = genieTable;
		this.melonPane = melonPane;
		this.geniePane = geniePane;
		this.startBtn = startBtn;
	}
	
	public void run() {
		startBtn.setEnabled(false);
		genieList = new ArrayList<MusicInfo>();
		melonList = new ArrayList<MusicInfo>();
		
		melonList = MusicChart.search("MELON");
		genieList = MusicChart.search("GENIE");
		
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
		startBtn.setEnabled(true);
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
	
}

public class UI implements ActionListener, WindowListener{
	private JFrame mainFrame;
	private JScrollPane melonPane, geniePane;
	private JTable melonTable, genieTable;
	private JButton startBtn;
	
	public UI() {		
		lauchFrame();
	}
	
	public void lauchFrame(){
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		//mainFrame.setLayout(new GridLayout(1, 1, 0, 0));
		mainFrame.setVisible(true);
		mainFrame.setSize(700, 500);
		
		startBtn = new JButton("시작");
		startBtn.addActionListener(this);
		mainFrame.add(startBtn, BorderLayout.EAST);
		
		JTabbedPane chartPane = new JTabbedPane(JTabbedPane.TOP);
		mainFrame.add(chartPane, BorderLayout.CENTER);
		
		melonPane = new JScrollPane();
		mainFrame.add(melonPane);
		chartPane.addTab("Melon", melonPane);
		
		geniePane = new JScrollPane();
		mainFrame.add(geniePane);
		chartPane.addTab("Jenie", geniePane);
		
	}
	
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == startBtn) {
			startBtn.setEnabled(false);
			SearchThread thread = new SearchThread(melonTable, genieTable, melonPane, geniePane, startBtn);
			Thread t1 = new Thread(thread);
			t1.start();
		}
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
	
	public static void main(String args[]) {
		new UI();
	}
	
}
