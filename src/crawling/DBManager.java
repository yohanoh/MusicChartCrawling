package crawling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBManager {
	private Connection con = null;
	
	public DBManager() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String userid = "tester";
		String passwd = "1234";
		
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 기존의 정보를 삭제함
			String sql = "DELETE MUSIC_CHART";
			pstmt = con.prepareStatement(sql);
			int n = pstmt.executeUpdate();
			System.out.println(n + "개의 행이 삭제됨");
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertInfo(ArrayList<MusicInfo> list) {
		String sql = "INSERT ALL\n";
		PreparedStatement pstmt = null;
		
		
		for(int i=0; i<list.size(); i++) {
			MusicInfo temp = new MusicInfo();
			temp = list.get(i);			
			sql = sql + String.format("INTO MUSIC_CHART VALUES(%d, '%s', '%s')\n", temp.getRank(), temp.getTitle(), temp.getSinger());

		}
		sql = sql + "SELECT *\nFROM DUAL";
		
		try {
			pstmt = con.prepareStatement(sql);	
			int n = pstmt.executeUpdate();
			System.out.println(n + "개의 행이 갱신됨");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public MusicSiteInfo selectMusicSite(String musicSite) {
		String query = String.format("SELECT * FROM MUSIC_SITE WHERE name = '%s'", musicSite);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MusicSiteInfo msi = new MusicSiteInfo();
				
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				msi.setUrls(rs.getString("url").split("\n"));
				msi.setTitle_parse(rs.getString("title_parser"));
				msi.setSinger_parse(rs.getString("singer_parser"));
				msi.setStart_point(rs.getString("start_point"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return msi;
	}
	
	
	
}
