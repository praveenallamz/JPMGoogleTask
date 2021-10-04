package googlePageObjects;

import org.openqa.selenium.By;

public class GPageObjects {

	 
	public static  By searchText = By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]");
	public static  By searchTextt = By.name("q");
	
	public static  By searchResult = By.id("result-stats");
	public static  By ListElements = By.xpath("//*[@id='rso']//h3"); ////*[@id='rso']//h3
	 
	
}
