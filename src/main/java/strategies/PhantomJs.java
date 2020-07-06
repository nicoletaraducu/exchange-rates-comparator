package strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class PhantomJs implements DriverStrategy {
    @Override
    public WebDriver setUpDriver() {
        System.setProperty("phantomjs.binary.path", "src/drivers/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
        return driver;
    }
}
