package amicaWebApp;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmicaEmpIns extends BaseClass {
	public static void main(String[] args) throws Exception {
		BaseClass.openbrowser();
		BaseClass.openapp();
		implicitWait(10);

		driver.findElement(By.cssSelector("#calculator-title")).click();
		implicitWait(3);
		WebElement income = driver.findElement(By.cssSelector("#calculator-annual-income"));
		income.clear();
		income.sendKeys("1000000");
		WebElement year = driver.findElement(By.cssSelector("#calculator-coverage-years"));
		Select years = new Select(year);
		years.selectByValue("20");
		implicitWait(4);
		driver.findElement(By.cssSelector("#calculator-extant-insurance-and-assets")).sendKeys("100000");
		driver.findElement(By.cssSelector("#calculate")).click();
		implicitWait(3);

		WebElement drpdn = driver.findElement(By.xpath(
				"//div[@class='group quote-coverage-container']//button[@class='btn dropdown-toggle selectpicker btn-default']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drpdn);
		explicitWait(drpdn);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", drpdn);
		implicitWait(3);
		dropDownForXpath("20 Year Level Term",
				"//div[@class='group quote-coverage-container']//div[@class='dropdown-menu open']//li");
		
		implicitWait(10);
		WebElement amount = driver
				.findElement(By.xpath("//div[@class='group quote-coverage-container']//input[@type='text']"));

		amount.clear();
		amount.sendKeys("$1000000");

		driver.findElement(By.cssSelector("button[data-id='healthCommon']")).click();

		dropDownForCSS("Really Healthy", "button[data-id='healthCommon']+div>ul>li");

		driver.findElement(By.cssSelector("button[data-id='smoker']")).click();
		dropDownForCSS("1 to 2 years ago", "button[data-id='smoker']+div>ul>li");

		driver.findElement(By.cssSelector("label[for='amica_chronic_No']")).click();
		WebElement sex = driver.findElement(By.cssSelector("label[for='sex_F']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sex);
		sex.click();

		dob("MM");
		dropDownForXpath("09",
				"//div[@class='group date-block--amica']//button[@title='MM']/following-sibling::div/ul/li");
		dob("DD");

		dropDownForXpath("23",
				"//div[@class='group date-block--amica']//button[@title='DD']/following-sibling::div/ul/li");

		dob("YYYY");
		dropDownForXpath("1994",
				"//div[@class='group date-block--amica']//button[@title='YYYY']/following-sibling::div/ul/li");

		height("heightFeet", "5");
		height("heightInches", "4");

		weight("135");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sex);

		name("chinngyung", "firstName");
		name("hfdsf", "lastName");

		driver.findElement(By.xpath("//input[@name='address']")).sendKeys("Po Box 417");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Colfax");
		driver.findElement(By.xpath("//button[@title='- Select -']")).click();

		dropDownForXpath("California", "//button[@title='- Select -']/following-sibling::div/ul/li");
		driver.findElement(By.xpath("//input[@name='zipCode']")).sendKeys("95713");
		driver.findElement(By.xpath("//div[@class='group inline']//input[@id='phoneNumber']"))
				.sendKeys("(530) 346-8361");

		WebElement email = driver.findElement(
				By.xpath("//div[@class='group terms-length-email textfield-block--email']//input[@name='email']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", email);
		email.sendKeys("abcd@gmail.com");

		driver.findElement(By.cssSelector("label[for='amica_currentCustomer_No']")).click();

		WebElement getQuote = driver
				.findElement(By.cssSelector("div[class='group navigation-block--get-quote']>div>div>button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getQuote);

		getQuote.click();
		Thread.sleep(60000);

		// (530) 346-8361
		// Po Box 417
		// Colfax, California(CA), 95713
	}

	/**
	 * 
	 */
	public static void name(String input, String id) {
		WebElement name = driver
				.findElement(By.xpath("//div[@class='group no-margin-bottom']//input[@id='" + id + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", name);
		name.sendKeys(input);
	}

	/**
	 * 
	 */
	public static void weight(String bodyweight) {
		driver.findElement(By.cssSelector("input[id='weightPounds']")).sendKeys(bodyweight);
	}

	/**
	 * 
	 */
	public static void height(String measure, String no) {
		driver.findElement(By.cssSelector("input[id='" + measure + "']")).sendKeys(no);
	}

	/**
	 * 
	 */
	public static void dob(String age) {

		driver.findElement(By.xpath("//div[@class='group date-block--amica']//button[@title='" + age + "']")).click();
		WebElement bday = driver
				.findElement(By.xpath("//div[@class='group date-block--amica']//button[@title='" + age + "']"));

	}

	/**
	 * @param drpdn
	 * @return
	 * @return
	 */
	public static void explicitWait(WebElement visibleElement) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.visibilityOf(visibleElement));

	}

	/**
	 * 
	 */
	public static void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	/**
	 * 
	 */
	private static void dropDownForXpath(String match, String locator) {
		List<WebElement> list = driver.findElements(By.xpath(locator));
		Iterator itr = list.iterator();
		while (itr.hasNext()) {

			WebElement reqElement = (WebElement) itr.next();

			String element = reqElement.getText();
			System.out.println(element);

			if (element.equalsIgnoreCase(match)) {

				reqElement.click();
				break;
			}

		}
	}

	private static void dropDownForCSS(String match, String locator) {
		List<WebElement> list = driver.findElements(By.cssSelector(locator));
		Iterator itr = list.iterator();
		while (itr.hasNext()) {

			WebElement reqElement = (WebElement) itr.next();

			String element = reqElement.getText();
			System.out.println(element);

			if (element.equalsIgnoreCase(match)) {

				reqElement.click();
				break;
			}

		}
	}

}
