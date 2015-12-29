import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;

import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.HomePage;

import java.io.File;

public class GettingHarTest{

    @Test
    public void bmpTest() throws Exception {
        ProxyServer server = new ProxyServer(4445);
        server.start();
        Proxy proxy = server.seleniumProxy();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        WebDriver driver = new FirefoxDriver(capabilities);
        server.newHar("issoft");
        driver.get("http://www.issoft.by/");
        driver.findElement(HomePage.MENU_TECHNOLOGIES_ITEM).click();

        Har har = server.getHar();
        har.writeTo(new File("D://issoft.har"));

        driver.quit();
        server.stop();
    }
}
