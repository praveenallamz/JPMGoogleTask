package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
 

/**
 * This DriverUtil class refer to browsers, os details, browser versions and
 * will close all browsers
 *
 */

public class DriverUtil {

	public static String IE = "IE";
	public static String REMOTE = "Remote";
	public static  String EDGE = "edge";
	public static  String CHROME = "Chrome";
	public static WebDriver browser = null;
	private static Map<String, WebDriver> drivers = new HashMap<>();
	public  static WebDriver driver = null;
	private static HashMap<String, String> checkLogin = new HashMap<>();
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	
	/**
	 * will use this getting browser(chrome, ie, ff)
	 * @param browserName
	 * @return
	 */
	public DriverUtil(){

	}


 

	/**
	 * @param browserName
	 * @return
	 * @throws InterruptedException 
	 * @throws Exception 
	 *
	 */ 
	
	   public static void windowHandler(String url) {
		 
		((JavascriptExecutor)GlobalUtil.getDriver()).executeScript("window.open()");
		    ArrayList<String> tabs = new ArrayList<String> (GlobalUtil.getDriver().getWindowHandles());
		    GlobalUtil.getDriver().switchTo().window(tabs.get(1)); //switches to new tab and url that want to open
		    GlobalUtil.getDriver().get(url);
		    
		
		    }
	     	 
	   public static void switchToParentWindow(){
		 
		 ((JavascriptExecutor)GlobalUtil.getDriver()).executeScript("window.open()");
		    ArrayList<String> tabs = new ArrayList<String> (GlobalUtil.getDriver().getWindowHandles());
		 GlobalUtil.getDriver().switchTo().window(tabs.get(0)); // switch back to main window
		 
	    }
	
	
	  public static WebDriver getBrowser(String exeEnv, String browserName){
		WebDriver browser = null;
		try{
			DesiredCapabilities caps;
			String URL = null; 

			if(exeEnv.equalsIgnoreCase(REMOTE)){

				if (browserName.equalsIgnoreCase(CHROME))
				{
					caps = DesiredCapabilities.chrome();
					caps.setCapability("os","WINDOWS");
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")){
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);;
					}
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("MAC")){

						caps.setCapability("os","OS X");
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
					}
				}
				else if(browserName.equalsIgnoreCase(IE))
				{
					caps = DesiredCapabilities.internetExplorer();
					caps.setCapability("os","WINDOWS");
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")){
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);;
					}
				}
				else if(browserName.equalsIgnoreCase(EDGE))
				{
					caps = DesiredCapabilities.edge();
					caps.setCapability("os","WINDOWS");
					caps.setCapability("version","14.0");
					caps.setCapability("os_version","10");;
				}

				else if(browserName.equalsIgnoreCase("Safari"))
				{
					caps = DesiredCapabilities.safari();
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")){
						caps.setCapability("os","OS X");
						caps.setCapability("os_version","SIERRA");
					}

					else{
						caps.setCapability("os","OS X");
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
					}
				}
				else{
					caps = DesiredCapabilities.firefox();
					caps.setCapability("os","WINDOWS");
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")){
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);;
					}
					if(GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("MAC")){
						caps.setCapability("os","OS X");
						caps.setCapability("os_version",GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
					}
				}

				if(GlobalUtil.getCommonSettings().getCloudProvider().equalsIgnoreCase("BrowserStack")){
					caps.setCapability("browserstack.debug", "true");
					caps.setCapability("build", GlobalUtil.getCommonSettings().getBuildNumber());
					URL = "https://" + GlobalUtil.getCommonSettings().getHostName() + ":" + GlobalUtil.getCommonSettings().getKey() + "@hub-cloud.browserstack.com/wd/hub";
				}
				try{
					browser  = new RemoteWebDriver(new URL(URL), caps);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				drivers.put(browserName, browser);


			}
			else{

				if (browserName.equalsIgnoreCase(CHROME)) {
					// Write code for chrome
					browser = drivers.get(browserName);
					if (browser == null) {
						//File chromeExecutable = new File(ConfigReader.getValue("ChromeDriverPath"));
						//System.setProperty("webdriver.chrome.driver", chromeExecutable.getAbsolutePath());
					    WebDriverManager.chromedriver().setup();
					    browser = new ChromeDriver();
				 	} // End if
				} else if (browserName.equalsIgnoreCase(IE)) {
					// Write code for IE
					browser = drivers.get(browserName);
					if (browser == null) {
						WebDriverManager.iedriver().setup();
					    browser = new InternetExplorerDriver();
						/*
						 * File ieExecutable = new File(ConfigReader.getValue("IEDriverPath"));
						 * System.setProperty("webdriver.ie.driver", ieExecutable.getAbsolutePath());
						 * DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
						 * capabilitiesIE.setCapability("ie.ensureCleanSession", true);
						 * capabilitiesIE.setCapability(InternetExplorerDriver.
						 * ENABLE_ELEMENT_CACHE_CLEANUP, true); browser = new
						 * InternetExplorerDriver(capabilitiesIE); drivers.put("IE", browser);
						 * checkLogin.put(browserName, "Y");
						 */
					}
				} 

				else {
					// Getting Firefox Browser
					browser = drivers.get("Firefox");
					if (browser == null) {
						
						WebDriverManager.firefoxdriver().setup();
					    browser = new FirefoxDriver();
					    
						/*
						 * File geckoExecutable = new File(ConfigReader.getValue("GeckoDriverPath"));
						 * System.out.println(geckoExecutable.getAbsolutePath());
						 * System.setProperty("webdriver.gecko.driver",geckoExecutable.getAbsolutePath()
						 * ); browser = new FirefoxDriver(); drivers.put("Firefox", browser)
						 */;
					}
				}
			}
			
			browser.manage().window().maximize();
			LogUtil.infoLog("TestStarted",GlobalUtil.getCommonSettings().getBrowser()+" : Browser Launched and " +
					"" +
					"Maximized.");
		}
		catch (Exception e) {
			LogUtil.errorLog(DriverUtil.class, "Browser not launched. Please check the configuration ", e);
			e.printStackTrace();
		}
		return browser;
		
	}
	/**
	 * will get browser type and version
	 * @param browser
	 * @param cap
	 * @return
	 */
	public static String getBrowserAndVersion(WebDriver browser, DesiredCapabilities cap) {
		String browserversion;
		String windows = "Windows";
		String browsername = cap.getBrowserName();
		// This block to find out IE Version number
		if ("IE".equalsIgnoreCase(browsername)) {
			String uAgent = (String) ((JavascriptExecutor) browser).executeScript("return navigator.userAgent;");
			LogUtil.infoLog(DriverUtil.class, uAgent);
			// uAgent return as "MSIE 8.0 Windows" for IE8
			if (uAgent.contains("MSIE") && uAgent.contains(windows)) {
				browserversion = uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf(windows) - 2);
			} else if (uAgent.contains("Trident/7.0")) {
				browserversion = "11.0";
			} else {
				browserversion = "0.0";
			}
		} else {
			
			browserversion = cap.getVersion();
		}
		String browserversion1 = browserversion.substring(0, browserversion.indexOf('.'));
		return browsername + " " + browserversion1;
	}
	
	public static void openUrl()
	{
		
		try 
		{
			driver.get("https://mail.google.com/");	
			
		} 
		catch (Throwable e) 
		{
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}
	
	
	
	/**
	 * will get operating system information 
	 * @return
	 */

	/**
	 * close all browsersw
	 * @return
	 */
	public static void closeAllDriver() {

		drivers.entrySet().forEach(key->
		{
			key.getValue().quit();
			key.setValue(null);
		});

		LogUtil.infoLog(DriverUtil.class, "Closing Browsers");
	}


	public static void getBrowser1(String cHROME2) {
		// TODO Auto-generated method stub
		
	}  

}// End class
