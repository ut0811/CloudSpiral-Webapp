package jp.kobe_u.cspiral.alpaca;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import jp.kobe_u.cspiral.alpaca.util.DBUtils;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class GetTicketInfoController {

	private final String TICKET_COLLECTION_NAME = "TICKET";
	private final String TRACLOG_COLLECTION_NAME = "TRACLOG";

	private DBCollection TICKET_COLLECTION;
	private DBCollection TRACLOG_COLLECTION;

	public GetTicketInfoController() {
		this.TICKET_COLLECTION = DBUtils.getInstance().getDb().getCollection(TICKET_COLLECTION_NAME);
		this.TRACLOG_COLLECTION = DBUtils.getInstance().getDb().getCollection(TRACLOG_COLLECTION_NAME);
	}

	public String TicketInfoInsertDBfromURL(String userId, String password, String urlString){
		String resultString="";
        final String user = userId;  // 設定する
        final String pass = password;  // 設定する
		Authenticator.setDefault(new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication()
		    {
		        final String username = user;  // 設定する
		        final String password = pass;  // 設定する

			return new PasswordAuthentication(username, password.toCharArray());
		    }
		});
		try {
			URL url= new URL(urlString);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			InputStream inStream = connection.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(inStream,"UTF-8"));

			String line = "";
			String[] itemList = input.readLine().split("\t");
			itemList[0] = itemList[0].substring(1, itemList[0].length());//一番初めの文字が文字化けする問題に対処できないため，1文字目をを取り除くという力技
//			for(int i=0;i<itemList.length;i++){
//				itemList[i]= new String(itemList[i].getBytes("EUC-JP"),"EUC-JP") ;
//			}
			TICKET_COLLECTION.drop();
			while ((line = input.readLine()) != null){
				String[] temp = line.split("\t");
				String jsonString = "{";
				for (int i = 0;i<temp.length;i++) {
					jsonString += "'" + itemList[i] + "':'" + temp[i] +"',";
				}
				jsonString = jsonString.substring(0, jsonString.length()-1);
				jsonString += "}";
				DBObject json = (DBObject) JSON.parse(jsonString);
				TICKET_COLLECTION.insert(json);

//				resultString += line;
			}
			resultString = "successful"+"("+user+")";
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			resultString = e.toString();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			resultString = e.toString();
		}
		return resultString;
	}



}
