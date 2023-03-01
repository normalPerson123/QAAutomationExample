package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {
    public AbstractPage(WebDriver driver){
        this.driver = driver;
    }

    public AbstractPage(WebDriver driver, long throttle){
        this.driver = driver;
        this.throttle = throttle;
    }

    protected WebElement waitVisible(By by, long duration){
        return new WebDriverWait(driver, Duration.ofSeconds(duration))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebDriver driver;
    private long throttle;
}
