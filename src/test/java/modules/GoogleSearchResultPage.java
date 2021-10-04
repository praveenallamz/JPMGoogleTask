package modules;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import googlePageObjects.GPageObjects;
import step_definitions.RunCukesTest;
import utilities.GlobalUtil;
import utilities.HTMLReportUtil;
import utilities.KeywordUtil;

public class GoogleSearchResultPage {

	public static WebDriver driverr;
 

	public static void searchForAnItem(HashMap<String, String> dataMap) throws Exception {

		// ENTER THE SEARCH STRING " J P Morgan"
		KeywordUtil.inputText(GPageObjects.searchText, dataMap.get("SearchText"), "Enter the search text");
		KeywordUtil.delay(1000);
		KeywordUtil.pressEnter(GPageObjects.searchText);

	}

	public static void ClickFirstLink() {

		driverr = GlobalUtil.getDriver();

		WebElement myDynamicElement = (new WebDriverWait(driverr, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
		List<WebElement> findElements = driverr.findElements(By.xpath("//*[@id='rso']//h3/a"));
		System.out.println(findElements.size());

		for (int i = 0; i < findElements.size(); i++) {
			findElements = driverr.findElements(By.xpath("//*[@id='rso']//h3"));
			findElements.get(0).click();
			break;
		}
		RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor("First Link Got Clicked"));

	}

	public static void VerifyLogo() {

		WebElement ImageFile = driverr.findElement(By.xpath("//img[contains(@alt,'J.P. Morgan logo')]"));
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driverr).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				ImageFile);
		if (!ImagePresent) {
			System.out.println("Image not displayed.");
		} else {
			System.out.println("Image displayed.");
			RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor("Logo verified"));
		}

		driverr.quit();
	}

}
