package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends AbstractPage{
    public SearchPage(WebDriver driver) {
        super(driver);
        throttle = 0;
    }

    public SearchPage(WebDriver driver, long throttle) {
        super(driver, throttle);
    }

    public int getNumPages() {
        int nPages = 0;
        try {
            Thread.sleep(throttle);
            List<WebElement> elements = driver.findElements(By.xpath(pageNavLis));
            //second to last button
            nPages = Integer.parseInt(elements.get(elements.size() - 2).getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nPages;
    }

    public List<String> getResultTitles() {
        List<String> resultTitles = driver.findElements(By.xpath(titles)).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        return resultTitles;
    }

    public void lastNavButtonClick() {
        try {
            Thread.sleep(throttle);
            driver.findElement(By.xpath(lastPageNavButton)).click();
        } catch(Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void lastResultAddToCartClick() {
        try {
            Thread.sleep(throttle);
            driver.findElement(By.xpath(lastResultAddToCartButton)).click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void viewCart() {
        try {
            try {
                //try the temporary dialogue
                Thread.sleep(throttle);
                driver.findElement(By.xpath(viewCartDialogButton)).click();
            } catch (Exception e) {
                //try the cart button
                Thread.sleep(throttle);
                driver.findElement(By.xpath(cartButton)).click();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void deleteCartItemClick() {
        try {
            Thread.sleep(throttle);
            driver.findElement(By.xpath(deleteButton)).click();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isCartEmpty(){
        boolean cartIsEmpty = false;
        try {
            Thread.sleep(throttle);
            waitVisible(By.xpath(cartEmptyDiv), 10);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return true;
    }




    //Selectors
    String pageNavLis = "//div[@id='paging']//li";
    //Navigates to the next page unless you're on the last page
    String lastPageNavButton = "//div[@id='paging']//li[last()]";
    String titles = "//div[@data-testid='productBoxContainer']//a[@data-testid='itemDescription']";
    String lastResultAddToCartButton = "(//input[@data-testid='itemAddCart'])[last()]";
    //This displays for 10 seconds or so
    String viewCartDialogButton = "//div[@data-role='notification']//a[contains(@href,'viewcart')]";
    String cartButton = "//a[@data-testid='cart-nav-link']";
    String deleteButton = "//button[contains(@class,'deleteCartItemButton')]";
    String cartEmptyDiv = "//div[@class='cartEmpty']";



    //Private attributes
    private long throttle;
}
