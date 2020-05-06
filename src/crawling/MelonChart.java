package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MelonChart {
	public static ArrayList<MusicInfo> search(){
		ArrayList<MusicInfo> list = new ArrayList<MusicInfo>();
		String url = "https://www.melon.com/chart/index.htm#params%5Bidx%5D=51";
		int rank = 1;

		
		try {
			Document doc = Jsoup.connect(url).get();
			Elements musicList = doc.select("tbody");
			
			Iterator<Element> title = musicList.select("div.ellipsis.rank01").iterator();
			Iterator<Element> singer = musicList.select("div.ellipsis.rank02 > span").iterator();
			
			while(title.hasNext()) {
				MusicInfo music = new MusicInfo();
				music.setRank(rank);
				music.setTitle(title.next().text().replace("'", "''"));
				music.setSinger(singer.next().text());
				list.add(music);
				rank++;
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	
	}
	
	public static void main(String args[]) {
		new DBManager(search());
	}
	
}
