package good.luck.hunting.job;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Reaper {

	public void reap(Resource inresource) {
		Document doc;
		try {
			doc = Jsoup
					.connect(inresource.BoardUrl)
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Accept-Language", "zh-cn")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36")
					.timeout(10000)
					.get();

			BYRLikeHtmlParser byrParser = new BYRLikeHtmlParser();

			byrParser.parser(doc, getRegex(), inresource);
			
			System.out.println(inresource.BoardName + ": done!");

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getRegex() {
		String regex = "";
		try {
			String temp = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("keywords.conf"),  "utf-8"));

			while ((temp = br.readLine()) != null) {
				regex = temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return regex;
	}
}
