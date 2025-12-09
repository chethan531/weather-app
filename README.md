# Weather Application - Java Servlet JSP

## 📋 Project Overview
A modern, production-ready weather application built with Java Servlet and JSP technologies. The application provides real-time weather information for any city worldwide through an intuitive web interface. Successfully upgraded to Java 21 LTS with modern API integration.

## ✨ Key Features
- 🌍 **Real-time Weather Data**: Fetch current weather information for any city globally
- 🌡️ **Comprehensive Metrics**: Display temperature, humidity, wind speed, visibility, cloud cover, and weather conditions
- 🎨 **Responsive UI**: Clean and modern interface with Font Awesome icons
- 🔄 **RESTful Integration**: Seamless integration with WeatherAPI.com
- ⚡ **Fast Performance**: Optimized servlet-based architecture
- 📱 **Mobile Friendly**: Responsive design for all devices

## 🛠️ Technologies & Tools

### Backend Technologies
- **Java 21 LTS** - Latest Long-Term Support version with modern features
- **Jakarta Servlet API 5.0.0** - Enterprise-grade servlet framework
- **Jakarta EE** - Modern Java EE platform
- **Maven 3.x** - Dependency management and build automation
- **Gson 2.10.1** - JSON parsing and serialization

### Frontend Technologies
- **JSP (JavaServer Pages)** - Dynamic server-side rendering
- **HTML5** - Modern semantic markup
- **CSS3** - Responsive styling
- **JavaScript** - Client-side validation and interactivity
- **Font Awesome 5.15.4** - Professional icon library

### Server & Runtime
- **Eclipse Jetty 11.0.15** - Lightweight, high-performance web server
- **Maven Jetty Plugin** - Integrated development server

### External APIs
- **WeatherAPI.com** - Reliable weather data provider
  - Real-time weather information
  - Comprehensive weather metrics
  - Global coverage

## 📁 Project Structure
```
weather-app-javaServletJSP/
├── src/
│   └── main/
│       ├── java/
│       │   └── MyPackage/
│       │       └── MyServlet.java      # Main servlet handling weather requests
│       └── webapp/
│           ├── index1.html             # Home page with search form
│           ├── index.jsp               # Weather details display page
│           ├── style.css               # Home page styles
│           ├── jsp-style.css           # JSP page styles
│           ├── script.js               # Client-side validation
│           ├── images/                 # Weather icons and images
│           ├── META-INF/
│           │   └── MANIFEST.MF
│           └── WEB-INF/
│               ├── web.xml             # Servlet configuration
│               └── lib/                # External libraries
├── pom.xml                             # Maven configuration
└── README.md                           # Project documentation
```

## 🚀 Setup & Installation

### Prerequisites
- **Java Development Kit (JDK) 21** - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Apache Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- **Git** - Version control system
- **WeatherAPI.com Account** - Free API key from [WeatherAPI.com](https://www.weatherapi.com/signup.aspx)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone 
   cd weather-app-javaServletJSP
   ```

2. **Configure API Key**
   - Sign up at [WeatherAPI.com](https://www.weatherapi.com/signup.aspx)
   - Get your free API key
   - Open `src/main/java/MyPackage/MyServlet.java`
   - Replace the API key on line ~32:
     ```java
     String myApiKey = "YOUR_API_KEY_HERE";
     ```

3. **Build the Project**
   ```bash
   mvn clean compile
   ```

4. **Run the Application**
   ```bash
   mvn jetty:run
   ```

5. **Access the Application**
   - Open browser and navigate to: `http://localhost:8080/index1.html`
   - Enter any city name and click "Get Weather"
   - View detailed weather information

## 🔧 Configuration Details

### Maven Configuration (pom.xml)
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
```

### Servlet Mapping (web.xml)
```xml
<servlet-mapping>
    <servlet-name>MyServlet</servlet-name>
    <url-pattern>/MyServlet</url-pattern>
</servlet-mapping>
```

## 💡 How It Works

### 1. User Input
- User enters city name in the search form on `index1.html`
- Form submits POST request to `/MyServlet`

### 2. Servlet Processing (MyServlet.java)
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String city = request.getParameter("city");
    String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + myApiKey + "&q=" + city;
    
    // Fetch data from WeatherAPI.com
    // Parse JSON response using Gson
    // Extract weather metrics
}
```

### 3. JSON Parsing
- Receive JSON response from WeatherAPI.com
- Parse using Gson library
- Extract relevant data:
  - Temperature (°C)
  - Humidity (%)
  - Wind Speed (km/h)
  - Visibility (km)
  - Cloud Cover (%)
  - Weather Condition
  - Date & Time

### 4. Data Display
- Set request attributes with weather data
- Forward to `index.jsp`
- Display using Expression Language (EL):
  ```jsp
  ${temperature}°C
  ${humidity}%
  ${weatherCondition}
  ```

## 🎯 API Integration

### WeatherAPI.com Integration
```java
// API Endpoint
http://api.weatherapi.com/v1/current.json?key={API_KEY}&q={CITY}&aqi=no

// Response Structure
{
  "location": {
    "name": "City Name",
    "localtime": "2025-12-09 15:23"
  },
  "current": {
    "temp_c": 32.3,
    "humidity": 36,
    "wind_kph": 16.2,
    "vis_km": 6.0,
    "cloud": 0,
    "condition": {
      "text": "Sunny"
    }
  }
}
```

## 🔐 Security Features
- Input validation on client and server side
- Error handling for invalid city names
- API key protection (stored server-side)
- Exception handling for network failures

## 📊 Performance Optimization
- Efficient JSON parsing with Gson
- Lightweight Jetty server
- Minimal dependencies
- Fast response times

## 🐛 Troubleshooting

### Server won't start
```bash
# Check if port 8080 is available
netstat -ano | findstr :8080

# Kill process if needed
taskkill /PID <process_id> /F
```

### API key errors
- Verify API key is correct in MyServlet.java
- Check WeatherAPI.com dashboard for quota limits
- Ensure no spaces in API key string

### Build failures
```bash
# Clean and rebuild
mvn clean install

# Update dependencies
mvn dependency:resolve
```

## 📈 Future Enhancements
- [ ] 5-day weather forecast
- [ ] Weather maps integration
- [ ] User location detection
- [ ] Weather alerts and notifications
- [ ] Historical weather data
- [ ] Multiple language support
- [ ] Dark mode theme
- [ ] PWA (Progressive Web App) support

## 🎓 Learning Outcomes
- Java Servlet lifecycle and request handling
- JSP and Expression Language (EL)
- RESTful API integration
- JSON parsing with Gson
- Maven build management
- Java 21 modern features
- Web application deployment

## 📝 License
This project is open source and available for educational purposes.




- WeatherAPI.com for providing weather data
- Font Awesome for icons
- Eclipse Jetty team
- Java community



Backend
src/main/java/
└── MyPackage/
    └── MyServlet.java              ⭐ MAIN BACKEND LOGIC
        ├── Handles HTTP POST requests from users
        ├── Fetches weather data from WeatherAPI.com
        ├── Parses JSON responses with Gson library
        ├── Processes and extracts weather metrics
        └── Forwards data to JSP for rendering
Backend Technologies:
Java 21 LTS - Server-side programming language
 Jakarta Servlet API - Handles HTTP requests/responses
 HttpURLConnection - Makes API calls to WeatherAPI.com
 Gson - Parses JSON data
Maven - Build and dependency management Jetty - Web application server


Front end
src/main/webapp/
├── index1.html                     ⭐ HOME PAGE (Landing page)
│   ├── Weather search form
│   ├── City input field
│   └── Get Weather button
│
├── index.jsp                       ⭐ RESULTS PAGE (Dynamic display)
│   ├── Temperature display
│   ├── Weather condition
│   ├── Humidity, wind speed, visibility
│   ├── Cloud cover percentage
│   └── Search form for new queries
│
├── style.css                       ⭐ HOME PAGE STYLING
│   └── Colors, layout, fonts for index1.html
│
├── jsp-style.css                   ⭐ RESULTS PAGE STYLING
│   └── Colors, layout, fonts for index.jsp
│
└── script.js                       ⭐ CLIENT-SIDE LOGIC
    ├── Form validation
    ├── User input checking
    └── Client-side error handling

    Frontend Technologies:

HTML5 - Page structure and semantic markup
CSS3 - Responsive styling and layout
JavaScript - Client-side validation and interactivity
Font Awesome Icons - Weather and UI icons
Responsive Design - Mobile-friendly interface

---
*Built with Java servelt and maven jetty server


