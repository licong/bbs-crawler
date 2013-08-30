package good.luck.hunting.job;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MailPostman {

	public boolean send(JSONObject pageResource, String toAddress) {
		boolean sendResult = false;

		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("**************"); // set your username
		mailInfo.setPassword("**************"); // set your password
		mailInfo.setFromAddress("*****@163.com"); // set your own email address
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(getTitle(pageResource));
		mailInfo.setContent(getContent(pageResource));

		sendResult = SimpleMailSender.sendHtmlMail(mailInfo);
		try {
			if (sendResult == true) {
				InfoPrinter.Sender(
						"[Successful!]" + pageResource.getString("title"),
						toAddress);
			} else {
				InfoPrinter.Sender(
						"[Failed!]" + pageResource.getString("title"),
						toAddress);
			}
		} catch (JSONException jsone) {
			jsone.printStackTrace();
		}
		return sendResult;
	}

	private String getTitle(JSONObject pageResource) {
		String titleResult = "";
		if (pageResource != null) {
			try {
				JSONArray keywords = (JSONArray) pageResource.get("keywords");
				for (int count = 0; count < keywords.length(); ++count) {
					titleResult = titleResult + "<" + keywords.get(count) + ">";
				}

				titleResult = titleResult + pageResource.getString("title");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return titleResult;
	}

	private String getContent(JSONObject pageResource) {
		String contentResult = "";
		try {
			contentResult = contentResult + "关键词： ";
			JSONArray keywords = (JSONArray) pageResource.get("keywords");
			for (int count = 0; count < keywords.length(); ++count) {
				contentResult = contentResult + "\"" + keywords.get(count) + "\""
						+ " ";
			}
			contentResult = contentResult + "\n ";

			contentResult = "文章链接: " + pageResource.getString("url") + "\n";
			contentResult = contentResult + "\n";

			contentResult = contentResult
					+ pageResource.getString("pageContent");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return contentResult;
	}
}
