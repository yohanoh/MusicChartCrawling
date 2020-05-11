package crawling;

public class MusicSiteInfo {
	private String start_point, title_parser, singer_parser;
	private String[] urls;
	
	public void setStart_point(String start_point) {
		this.start_point = start_point;
	}
	
	public String getStart_point() {
		return start_point;
	}
	
	public void setTitle_parse(String title_parser) {
		this.title_parser = title_parser;
	}
	
	public String getTitle_parser() {
		return title_parser;
	}
	
	public void setSinger_parse(String singer_parser) {
		this.singer_parser = singer_parser;
	}
	
	public String getSinger_parser() {
		return singer_parser;
	}
	
	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	
	public String[] getUrls() {
		return urls;
	}
}
