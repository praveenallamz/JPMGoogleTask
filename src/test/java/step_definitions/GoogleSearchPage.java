package step_definitions;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.ExcelDataUtil;
import utilities.GlobalUtil;
import utilities.KeywordUtil;

public class GoogleSearchPage extends KeywordUtil {

	@SuppressWarnings("rawtypes")
	static Class thisClass = GoogleSearchPage.class;
	static String testCaseID = thisClass.getSimpleName();

	static String logStep;
	public static WebDriver driver;
	public static HashMap<String, String> dataMap = new HashMap<String, String>();
	String email = "testing" + KeywordUtil.getCurrentDateTime() + "@testing.com";

	@Given("^Read the test data  \"([^\"]*)\" from Excel file$")
	public void read_the_test_data_from_Excel_file(String arg1) {
		try {
			KeywordUtil.cucumberTagName = "Web";
			dataMap = ExcelDataUtil.getTestDataWithTestCaseID("Amazon", arg1);

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Navigate to the url$")
	public void navigate_to_the_url() {
		try {
			navigateToUrl(dataMap.get("URL"));

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Search the given text$")
	public void search_the_given_text() throws Throwable {

		try {
			modules.GoogleSearchResultPage.searchForAnItem(dataMap);
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^Add Click the first link$")
	public void add_Click_the_first_link() throws Throwable {

		try {

			modules.GoogleSearchResultPage.ClickFirstLink();
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@Then("^Verify Logo is Displayed$")
	public void verify_Logo_is_Displayed() throws Throwable {

		try {
			// KeywordUtil.click(ProductDetailPage.checkCart,"Click on Cart");
			modules.GoogleSearchResultPage.VerifyLogo();
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.ErrorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

}
