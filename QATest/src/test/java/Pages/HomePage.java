package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends AbstractPage{
    public HomePage(WebDriver driver) {
        super(driver);
        throttle = 0;
    }

    public HomePage(WebDriver driver, long throttle) {
        super(driver, throttle);
    }

    public void searchFieldType(String searchTerms) {
        try{
            Thread.sleep(throttle);
            driver.findElement(By.xpath(searchField)).sendKeys(searchTerms);
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public void searchButtonClick() {
        try{
            Thread.sleep(throttle);
            driver.findElement(By.xpath(searchButton)).click();
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    public void pageNavigatorLastPageClick() {
        try{
            Thread.sleep(throttle);
            driver.findElement(By.xpath(searchButton)).click();
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    //Selectors
    private String searchField = "//input[@id='searchval']";
    private String searchButton = "//button[@value='Search'][@type='submit']";

    //Private attributes
    private long throttle;
}
