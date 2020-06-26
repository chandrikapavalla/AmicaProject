package amicaWebApp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	public static WebDriver driver;
	public static void openbrowser() {
		
		System.setProperty("webdriver.chrome.driver","E:/chromedriver_win32/chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	public static void openapp() throws Exception {
		driver.navigate().to("https://amicalifeconsolidated.croninqa2.com/employee");
		Thread.sleep(3000L);
	}
	
	
	
	public static void closebrowser() {
		driver.quit();
	}
	
	
	

}
