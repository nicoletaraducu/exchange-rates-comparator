package strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {
    @Override
    public WebDriver setUpDriver() {
        System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
}
