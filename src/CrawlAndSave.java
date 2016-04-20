import java.io.File;
import java.util.Vector;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;

public class CrawlAndSave {
	
	private String[] patterns;
	private String dir;
	private Integer c;
	
	public CrawlAndSave(String s,String d) {
		patterns = s.split(";");
		dir = d;
		c=0;
		new File(d).mkdir();
		for(String t : patterns) {
			new File(dir+"/"+t).mkdir();
		}
	}
	
	/**
	 * Downloads the contents of the URL
	 * @param url
	 * @throws Exception
	 */
	public void save (String url) throws Exception {
		UserAgent ua = new UserAgent();
		for(String s : patterns) {
			if(url.toLowerCase().indexOf(s.toLowerCase(), 0) != -1) {
				ua.visit(url);
				ua.doc.saveCompleteWebPage(new File(dir+"/"+s+"/"+ c +".htm"));
				System.out.println("Downloaded "+url+" to "+dir+"/"+s+"/"+ c +".htm");
				c++;
			}
		}
	}
	
	/**
	 * Returns a list of URLs on the URL page given as the parameter
	 * @param url
	 * @returns Vector<String>
	 * @throws Exception
	 */
	public Vector<String> crawl (String url) throws Exception{
		System.out.println("Crawling "+url);
		UserAgent ua = new UserAgent();
		Vector<String> ans = new Vector<String>();
		ua.visit(url);
		Elements l = ua.doc.findEvery("<a href>");
		for(Element e : l) {
			ans.add(e.getAtString("href"));
		}
		return ans;
	}
}
