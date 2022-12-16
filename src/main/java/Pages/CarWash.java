package Pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Base.Base;
public class CarWash extends Base{
	By city = By.id("city");
	By choice = By.id("Hyderabad");
	By search=By.id("srchbx");
//	By care = By.id("distdrop_rat");
	By button = By.id("srIconwpr");
	By RatingsIcon = By.id("distdrop_rat");
	By Ratings = By.xpath("//span[@class='green-box']");
	By Names = By.xpath("//span[@class='lng_cont_name']");
	By cont = By.xpath("//ul[contains(@class,'padding')]//child::li//p[contains(@class,'contact')]//span/a");//ul[contains(@class,'padding')]//child::li//p[contains(@class,'contact')]//span/a
	By vote = By.xpath("//p/a/span[@class='rt_count lng_vote']");
	By rate =By.className("lng_srtfltr");
	public void carwash() throws InterruptedException {
		logger = report.createTest("Getting Car Washes greater than 4.0 rating.");
		try {
		openURL("websiteURLKey");
		Screenshoot("Home");
		driver.findElement(city).click();
		driver.findElement(choice).click();
		driver.findElement(search).click();
		driver.findElement(search).sendKeys("Car Wash Services");
		Thread.sleep(1000);
		driver.findElement(button).click();
		Thread.sleep(2000);
		driver.findElement(RatingsIcon).click();
		Screenshoot("Car wash");
		reportPass("Car wash Page is clicked");
		List<WebElement> ratings = driver.findElements(Ratings);
		List<WebElement> voting = driver.findElements(vote);
		List<WebElement> names = driver.findElements(Names);
	//	List<WebElement> contacts = driver.findElements(cont);

		System.out.println("List of Car Wash Services with >4 Ratings");
		//System.out.println(voting.size());
		//System.out.println(contacts.size());  voting.size()
		for (int i = 0; i<5 ; i++) {
			float rate = Float.parseFloat(ratings.get(i).getText());
			String str = voting.get(i).getText();
			String[] s= str.split(" ");
			int votes = Integer.parseInt(s[0]);
			if (rate > 4 && votes > 20  ) {
				System.out.println(ratings.get(i).getText() + " - "+ names.get(i).getText());				
				reportPass("Car Washes name and ratings are obtained.");
			}
		}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}

