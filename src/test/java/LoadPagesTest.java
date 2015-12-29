import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.OutsourcingPage;
import pages.TechnologiesPage;

import java.lang.String;
import java.util.concurrent.TimeUnit;

public class LoadPagesTest{
    private WebDriver driver;
    private final static String BASE_URL = HomePage.HOME_PAGE_URL;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test
    public void LoadHomePageTest(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart) + " milliseconds.");
    }

    @Test
    public void LoadTechnologiesPageTest(){
        driver.findElement(HomePage.MENU_TECHNOLOGIES_ITEM).click();
        Assert.assertEquals(driver.getCurrentUrl(), TechnologiesPage.TECHNOLOGIES_PAGE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart) + " milliseconds.");
    }

    @Test
    public void LoadOutsourcingPageTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(HomePage.MENU_OUR_SERVICES_ITEM)).perform();
        Thread.sleep(1000);
        actions.moveToElement(driver.findElement(HomePage.MENU_OUTSOURCING_ITEM)).click().perform();
        Assert.assertEquals(driver.getCurrentUrl(), OutsourcingPage.OUTSOURCING_PAGE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("Page Load Time is " + (loadEventEnd - navigationStart) + " milliseconds.");
    }
}
