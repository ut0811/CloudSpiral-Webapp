package jp.kobe_u.cspiral.alpaca;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

public class Sample {

	public static void main(String[] args){
		Authenticator.setDefault(new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication()
		    {
		        final String username = "y-furuta";  // 設定する
		        final String password = "1234";  // 設定する

			return new PasswordAuthentication(username, password.toCharArray());
		    }
		});
		try
		{
			String userId = "y-furuta";
			String password = "1234";
			URL url = new URL("http://ec2-52-69-42-30.ap-northeast-1.compute.amazonaws.com/trac/query?status=accepted&status=assigned&status=closed&status=new&status=reopened&format=tab&col=id&col=summary&col=status&col=type&col=priority&col=milestone&col=component&col=version&col=resolution&col=time&col=changetime&col=estimatedhours&col=startdate&col=totalhours&col=enddate&col=reporter&col=keywords&col=cc&report=18&order=priority");
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			InputStream inStream = connection.getInputStream();
			BufferedReader input =
			new BufferedReader(new InputStreamReader(inStream));

			String line = "";
			while ((line = input.readLine()) != null)
			System.out.println(line);
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}
