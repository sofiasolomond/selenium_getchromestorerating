package base;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTests {

	private WebDriver driver;
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get("https://chrome.google.com/webstore/search/norton");
	}
	public void tearDown() {
		this.driver.quit();
	}
	public void getRating() {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement s = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Y89Uic")));
		List<WebElement> allProducts =  this.driver.findElements(By.className("a-na-d-B1neQd-cb"));
		System.out.println("\n\nTotal Norton products " + allProducts.size());
		for ( WebElement product : allProducts) {
			WebElement rating = product.findElement(By.xpath(".//div[@class='Y89Uic']"));
			WebElement productName = product.findElement(By.xpath(".//div[@class='a-na-d-w']"));
			if (productName.getText().toLowerCase().contains("norton")) {
			String ratingText = rating.getAttribute("title");
			String[] splitUp = ratingText.split(" ");
			System.out.println("Rating for  " + productName.getText() + " is " + splitUp[2] + "\n");
			}
		}
	
	}
	public static void main(String args[]){
	     BaseTests test = new BaseTests();
	     test.setUp();
	     test.getRating();
	     test.tearDown();
	}
}
