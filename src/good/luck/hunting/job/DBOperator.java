package good.luck.hunting.job;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBOperator {
	private String driver = "com.mysql.jdbc.Driver";
	private String dbURL = "jdbc:mysql://127.0.0.1:3306/jobrepo";
	private String dbUsername = "root";
	private String dbPassowrd = "";
	private Connection dbConnection;
	
	DBOperator() {
		init();
	}
	
	public void setResource (String inKey, JSONObject inResource, String inDBTable) {
		String replaceSQL = "INSERT INTO " + inDBTable +" VALUES (?,?,?)";
		try {
			if (!dbConnection.isClosed()) {
				PreparedStatement ps = dbConnection.prepareStatement(replaceSQL);
				ps.setInt(1, Integer.parseInt(inKey));
				ps.setObject(2, inResource.toString().getBytes("utf-8"));
				ps.setString(3, "NOTSEND");
				ps.execute();
				ps.close();
				InfoPrinter.ReaperHit(inResource.getString("title"));
			}
		} catch (SQLException sqle) {
			try {
				InfoPrinter.ReaperIgn(inResource.getString("title"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	

	public void setidSENT (int inKey, String inDBTable) {
		String replaceSQL = "UPDATE " + inDBTable +" set emailStatus=? WHERE id=?";
		try {
			if (!dbConnection.isClosed()) {
				PreparedStatement ps = dbConnection.prepareStatement(replaceSQL);
				ps.setString(1, "SENT");
				ps.setInt(2, inKey);
				ps.execute();
				ps.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
	}
	

	public JSONArray getidNOTSEND (String inDBTable) {
		
		String selectNOTSEND = "SELECT id FROM " + inDBTable + " WHERE emailStatus=?";
		JSONArray idJArray = new  JSONArray();
		try {
			if (!dbConnection.isClosed()) {
				PreparedStatement ps = dbConnection.prepareStatement(selectNOTSEND);
				ps.setString(1, "NOTSEND");
				ResultSet rs = ps.executeQuery();
				int counter = 0;
				while (rs.next()) {
					idJArray.put(counter, rs.getInt("id"));
					++counter;
				}
				ps.close();
			}
		} catch (SQLException sqle) {
		} catch (JSONException e) {
			e.printStackTrace();
		}

		
		return  idJArray;
	}

	public JSONObject getResource(int inKey, String inDBTable) {
		String selectSQL = "SELECT pageResource FROM " + inDBTable + " WHERE id=?";
		JSONObject JSONResult = null;
		try {
			if (!dbConnection.isClosed()) {
				PreparedStatement ps = dbConnection.prepareStatement(selectSQL);
				ps.setInt(1, inKey);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					JSONObject JSONRS = new JSONObject(rs.getString("pageResource"));
					JSONResult = JSONRS;
				}
				ps.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSONResult;
	}
	
	private void init() {
		try {
			Class.forName(driver);
			dbConnection = DriverManager.getConnection(dbURL, dbUsername,
					dbPassowrd);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws SQLException {
		if (!dbConnection.isClosed()) {
			dbConnection.close();
		}
	}
}
