package crawling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManager {
	private ArrayList<MusicInfo> list;
	public DBManager(ArrayList<MusicInfo> list) {
		this.list = list;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String userid = "tester";
		String passwd = "1234";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String sql = "INSERT ALL\n";
			for(int i=0; i<list.size(); i++) {
				MusicInfo temp = new MusicInfo();
				temp = list.get(i);
				
				sql = sql + String.format("INTO MUSIC_CHART VALUES(%d, '%s', '%s')\n", temp.getRank(), temp.getTitle(), temp.getSinger());
				
				
			}
			sql = sql + "SELECT *\nFROM DUAL";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int n = pstmt.executeUpdate();
			System.out.println(n+"개의 행 갱신");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
