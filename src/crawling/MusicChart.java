package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MusicChart {
	public static ArrayList<MusicInfo> search(String musicSite){
		ArrayList<MusicInfo> list = new ArrayList<MusicInfo>();
		DBManager db = new DBManager();
		MusicSiteInfo msi = db.selectMusicSite(musicSite);
		
		String[] urls = msi.getUrls();
		String start_point = msi.getStart_point();
		String title_parser = msi.getTitle_parser();
		String singer_parser = msi.getSinger_parser();
		
		int rank = 1;
		
		try {			
			for(String url:urls) {
				
				Document doc = Jsoup.connect(url).get();
				Elements musicList = doc.select(start_point);
				
				Iterator<Element> title = musicList.select(title_parser).iterator();
				Iterator<Element> singer = musicList.select(singer_parser).iterator();
				
				while(title.hasNext()) {
					MusicInfo music = new MusicInfo();
					music.setRank(rank);
					music.setTitle(title.next().text().replace("'", "''"));
					music.setSinger(singer.next().text().replace("'", "''"));
					list.add(music);
					rank++;
				}
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}
	
}
