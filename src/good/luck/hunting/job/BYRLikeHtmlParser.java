package good.luck.hunting.job;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BYRLikeHtmlParser {

	public void parser(Document indoc, String inregex, Resource resource)
			throws JSONException, SQLException, IOException {
		Elements tr = indoc.getElementsByTag("tr").select("tr:not(.top)");
		Elements td = tr.select("td.title_9");
		Elements a = td.select("a");
		Pattern pattern = Pattern.compile(inregex);

		DBOperator dbo = new DBOperator();

		for (int counter = 0; counter < a.size(); ++counter) {
			Element element = a.get(counter);
			String title = element.text();
			String absurl = element.absUrl("href");
			String relPath = element.attr("href");
			String id = relPath.replace(resource.RelPath, "");
			JSONObject selectedKeyWords = titleSelector(title, pattern);
			String pageContent = pageContentFetcher(absurl);
			if (selectedKeyWords != null) {
				JSONObject pageResource = resourceserialization(title, absurl,
						selectedKeyWords, pageContent);
				
				dbo.setResource(id, pageResource, resource.DBTable);
			}
		}

		dbo.close();
	}

	// ѡ????ƥ???ؼ??ʵ?title
	private JSONObject titleSelector(String intitle, Pattern inpattern) {
		JSONObject selectedKeyWords = null;
		Matcher matcher = inpattern.matcher(intitle);

		while (matcher.find()) {
			String keyWord = matcher.group();
			try {
				if (selectedKeyWords == null) {
					selectedKeyWords = new JSONObject();
				}
				selectedKeyWords.put(keyWord, keyWord);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return selectedKeyWords;
	}

	private String pageContentFetcher(String pageUrl) {
		String contentResult = "";
		Document doc;
		try {
			doc = Jsoup
					.connect(pageUrl)
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Accept-Language", "zh-cn")
					.header("X-Requested-With", "XMLHttpRequest")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36")
					.timeout(10000)
					.get();
			
			Element content = doc.select("td.a-content").first();
			content.select("font").remove();
			contentResult = content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentResult;
	}
	
	private JSONObject resourceserialization(String intitle, String inabsurl,
			JSONObject inkeyWords, String inpageContent) {
		JSONObject resource = new JSONObject();
		JSONArray keywords = inkeyWords.names();
		
		try {
			resource.put("title", intitle);
			resource.put("url", inabsurl);
			resource.put("keywords", keywords);
			resource.put("pageContent", inpageContent);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return resource;

	}
}
