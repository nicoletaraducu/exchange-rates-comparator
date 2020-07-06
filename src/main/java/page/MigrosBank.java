package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.StringConstants;

public class MigrosBank {

    private WebDriver driver;
    private Float EurBuy;
    private Float EurSell;

    public Float getEurBuy() {
        return EurBuy;
    }

    public void setEurBuy(Float eurBuy) {
        EurBuy = eurBuy;
    }

    public Float getEurSell() {
        return EurSell;
    }

    public void setEurSell(Float eurSell) {
        EurSell = eurSell;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebsite() {
        driver.get(StringConstants.MIGROS_BANK_WEBSITE);
    }

    public void eurExhange() {
        String resultBuy;
        String resultSell;
        resultBuy = driver.findElement(By.cssSelector("#\\31 912 > div > table > tbody > tr:nth-child(2) > td:nth-child(5) > span > strong > em")).getText();
        resultSell = driver.findElement(By.cssSelector("#\\31 912 > div > table > tbody > tr:nth-child(2) > td:nth-child(6) > span > strong > em")).getText();
        resultBuy = resultBuy.replace(',', '.');
        resultSell = resultSell.replace(',', '.');
        this.EurBuy = Float.parseFloat(resultBuy);
        this.EurSell = Float.parseFloat(resultSell);
    }

    public void closeDriver() {
        driver.close();
    }
}
