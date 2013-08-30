package good.luck.hunting.job;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MAIN_REAP_JOB {

	public static void main(String[] args) throws IOException, JSONException,
			SQLException {

		Timer reapertimer = new Timer();
		reapertimer.schedule(new ReaperTask(), 1000, 50000);

		Timer sendertimer = new Timer();
		sendertimer.schedule(new SenderTask(), 18000, 300000);
	}
}

class ReaperTask extends java.util.TimerTask {
	@Override
	public void run() {
		ResourceRepo rRepo = new ResourceRepo();
		Reaper reaper = new Reaper();
		reaper.reap(rRepo.BYR_BYS);
		reaper.reap(rRepo.BYR_ZPXX);
		reaper.reap(rRepo.NS_LT);
		reaper.reap(rRepo.NS_SZ);
		reaper.reap(rRepo.NS_XZ);
		InfoPrinter.ReaperDoneTime();
	}
}

class SenderTask extends java.util.TimerTask {
	@Override
	public void run() {
		ResourceRepo rRepo = new ResourceRepo();
		
		String toAddress = "*********@gmail.com";

		sendMail(rRepo.BYR_BYS.DBTable, toAddress);
		sendMail(rRepo.BYR_ZPXX.DBTable, toAddress);
		sendMail(rRepo.NS_LT.DBTable, toAddress);
		sendMail(rRepo.NS_SZ.DBTable, toAddress);
		sendMail(rRepo.NS_XZ.DBTable, toAddress);
		InfoPrinter.SenderDoneTime();
	}

	public void sendMail(String inDBtable, String toAddress) {
		try {
			DBOperator dbo = new DBOperator();
			JSONArray idset = dbo.getidNOTSEND(inDBtable);

			for (int counter = 0; counter < idset.length(); counter++) {

				int id = (int) idset.get(counter);
				String table = inDBtable;
				JSONObject resource = dbo.getResource(id, table);

				MailPostman mp = new MailPostman();

				mp.send(resource, toAddress);

			}

			dbo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
