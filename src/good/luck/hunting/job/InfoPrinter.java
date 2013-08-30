package good.luck.hunting.job;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoPrinter {
	public static void ReaperHit(String text) {
		System.out.println("[Reaper] Hit: " + text);
	}
	
	public static void ReaperIgn(String text) {
		System.out.println("[Reaper] Ign: " + text);
	}
	
	public static void Sender(String toaddress, String text) {
		System.out.println("[Sender] Send to " + toaddress + ": " + text);
	}
	
	public static void ReaperDoneTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Reap Done! @ " + df.format(new Date()));
	}
	
	public static void SenderDoneTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Send Done! @ " + df.format(new Date()));
	}
}
