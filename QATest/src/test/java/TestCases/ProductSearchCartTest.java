package TestCases;

import Pages.HomePage;
import Pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Test
public class ProductSearchCartTest {
    private WebDriver driver;
    private HomePage homePage;
    private SearchPage searchPage;

    @Test
    public void searchTableCartAddRemove() {
        //parameterize the url for other environments in real life
        driver.get("https://www.webstaurantstore.com");
        homePage.searchFieldType("stainless work table");
        homePage.searchButtonClick();
        //Get n page buttons
        //There are a few different ways we could tell if we're on the last page.
        //For now we will just go by the number in the second to last page nav button.
        int nPages = searchPage.getNumPages();
        SoftAssert softAssert = new SoftAssert();
        //Iterate through pages
        for(int i = 0; i < nPages; i++) {
            //get result titles
            List<String> resultTitles = searchPage.getResultTitles();
            //Assert that they have the word 'Table' in them
            for(String j : resultTitles){
                softAssert.assertTrue(j.contains("Table"),
                        "Expected text to contain 'Table' Page: "
                                + (i + 1) + " Text: " + j);
            }
            //If we're not on the last page, go to the last page
            //Could just use the URL depending on what you prefer
            if (i != nPages - 1) {
                searchPage.lastNavButtonClick();
            }
        }
        //now on the last page
        //add the last result to the cart
        searchPage.lastResultAddToCartClick();
        //nav to cart
        searchPage.viewCart();
        //assert item is in the cart
        //remove item
        searchPage.deleteCartItemClick();
        //assert cart is empty
        softAssert.assertTrue(searchPage.isCartEmpty(), "Cart Empty div showing");
        //run all asserts
        softAssert.assertAll();
    }

    //Could be moved to an abstract class
    //Could be parameterized for other browsers
    @BeforeTest
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        //don't want to hard code throttle value in real life
        long throttle = 1000;
        homePage = new HomePage(driver, throttle);
        searchPage = new SearchPage(driver, throttle);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
