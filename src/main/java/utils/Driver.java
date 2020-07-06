package utils;

import org.openqa.selenium.WebDriver;
import strategies.DriverStrategy;

import java.util.concurrent.TimeUnit;

public class Driver {
    private WebDriver driver;

    public WebDriver instantiate(DriverStrategy driverStrategy) {
        this.driver = driverStrategy.setUpDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(StringConstants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver(){
        this.driver.close();
    }
}
