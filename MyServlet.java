package MyPackage;

import jakarta.servlet.ServletException;
import java.util.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serial;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//WeatherAPI.com setup
	String myApiKey = "3e52067407a84843add93300250912";
	
	//getting the city name from the form input
	String city = request.getParameter("city");
		//Create the URL for WeatherAPI.com
		String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + myApiKey + "&q=" + city + "&aqi=no";
		
		try {
			//API Integration
			URL url = URI.create(apiUrl).toURL();
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			
			//reading data from network;
			InputStream inpStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inpStream);
			
			//want to store in string
			StringBuilder responseContent = new StringBuilder();
			
			//create scanner object  to take input from reader.
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNext()) {
				responseContent.append(scanner.nextLine());
			}
			scanner.close();
			
			//now the response should be made in to json format
			// typecasting 
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
			System.out.println(jsonObject);
			
			
			//Temperature - WeatherAPI.com returns temp in Celsius directly
			int tempInCelsius = jsonObject.getAsJsonObject("current").get("temp_c").getAsInt();
			
			//Humidity
			int humidity = jsonObject.getAsJsonObject("current").get("humidity").getAsInt();
			
			//wind speed - WeatherAPI returns in kph
			double windSpeed = jsonObject.getAsJsonObject("current").get("wind_kph").getAsDouble();
			
			//visibility - WeatherAPI returns in km
			int visibility = jsonObject.getAsJsonObject("current").get("vis_km").getAsInt();
			
			//weather condition
	        String weatherCondition = jsonObject.getAsJsonObject("current").getAsJsonObject("condition").get("text").getAsString();
	        
	        //cloud cover
	        int cloudCover = jsonObject.getAsJsonObject("current").get("cloud").getAsInt();
	        
	        
	        // Date & Time - Get from WeatherAPI
	        String locationTime = jsonObject.getAsJsonObject("location").get("localtime").getAsString();
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date dateObj = inputFormat.parse(locationTime);
	        
	        SimpleDateFormat sdfDate = new SimpleDateFormat("EEE MMM dd yyyy");
	        String date = sdfDate.format(dateObj);

	        // Fetching the time
	        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	        String formattedTime = sdfTime.format(dateObj);


	        // Set the data as request attributes (for sending to the jsp page)
	        request.setAttribute("date", date);
	        request.setAttribute("city", city);
	        request.setAttribute("visibility",visibility);
	        request.setAttribute("temperature", tempInCelsius);
	        request.setAttribute("weatherCondition", weatherCondition); 
	        request.setAttribute("humidity", humidity);    
	        request.setAttribute("windSpeed", windSpeed);
	        request.setAttribute("cloudCover", cloudCover);
	        request.setAttribute("currentTime", formattedTime);
	        request.setAttribute("weatherData", responseContent.toString());
	        
	        connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
        
     // Forward the request to the weather.jsp page for rendering
        request.getRequestDispatcher("index.jsp").forward(request, response);

        
	}

}
