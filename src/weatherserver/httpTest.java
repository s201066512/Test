package weatherserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
public class httpTest {
	private static String readAll (Reader rd) throws IOException{
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char)cp);
		}
		return sb.toString();
	}
	public static JSONObject readJsonFromUrl (String url) throws IOException, JSONException{
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		}
		finally {
			is.close();
		}
	}
	
	public static void main (String args[]) throws IOException, JSONException{
		JSONObject json = readJsonFromUrl("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m");
		
		/* to get the current temperature i convert the json to a string and then put into an array but the array isn't even properly formatted 
		 * as in it just puts every single thing into an array so to get the value that i want i need to trim the string...
		 * this way of getting the current temperature is not the best but it works so it is what it is
		 * */
		String data = json.toString();
		String[] time = data.split(",");
		String currentTemp = (time[8]).substring(28);
		//System.out.println(currentTemp);
		
		for (String a: time) {
			System.out.println(a);
		}
	}
}
