package weatherserver;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class httpTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        	StringBuilder responseBuilder = new StringBuilder();
        	String line;
        	while ((line = in.readLine()) != null) {
        	    responseBuilder.append(line);
        	}
        	String response = responseBuilder.toString();   
        	System.out.println(response);
        } 
    }
}
