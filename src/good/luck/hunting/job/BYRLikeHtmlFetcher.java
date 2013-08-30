package good.luck.hunting.job;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BYRLikeHtmlFetcher {
	
	public Document fetch(String inResourceUrl) {
		Document doc = null;
		try {
			doc = Jsoup
					.connect(inResourceUrl)
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Accept-Language", "zh-cn")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36")
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
