package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.StringConstants;


public class SwissNationalBank {
    private WebDriver driver;
    private Float eurExchange;

    public Float getEurExchange() {
        return eurExchange;
    }

    public void setEurExchange(Float eurExchange) {
        this.eurExchange = eurExchange;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebsite() {
        driver.get(StringConstants.SNB_WEBSITE);
    }

    public void clickExchangeRates() {
        final WebElement Exchange_Rates = driver.findElement(By.cssSelector("#t3 > h2 > span"));
        Exchange_Rates.click();
    }

    public void eurExchange() {

        WebDriverWait wait = new WebDriverWait(driver, StringConstants.WAIT_INTERVAL);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#t3 > div > div > ul.rates-values.exchangerates > li:nth-child(1) > div.rates-values-item-wrapper > div > span.value"))));
        this.eurExchange = Float.parseFloat(element.getText());

    }

    public void closeDriver() {
        driver.close();
    }
}
