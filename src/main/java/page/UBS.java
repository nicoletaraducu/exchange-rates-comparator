package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.StringConstants;

public class UBS {

    private WebDriver driver;
    private Float EuroBuy;
    private Float EuroSell;

    public Float getEuroBuy() {
        return EuroBuy;
    }

    public void setEuroBuy(Float euroBuy) {
        EuroBuy = euroBuy;
    }

    public Float getEuroSell() {
        return EuroSell;
    }

    public void setEuroSell(Float euroSell) {
        EuroSell = euroSell;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private static final By CURRENCY_CONVERTER = By.cssSelector("#main > main > div:nth-child(2) > div > div > div > div > div.gridcontrol2__base.small > div > div > div > div.basecomponent.messagestage.messagestage__base > div > div > div > div > div > div > a > div.messagestage__content--bottom.grid-messagestage__content--small > div > div > span > span > span > span > span");
    private static final By CHANGE_ACTION = By.xpath("//*[@id=\"grt_scrollarea\"]/form/div/table/tbody/tr[1]/td/div/div[2]/div[1]");

    public void openWebsite() {
        driver.get(StringConstants.UBS_WEBSITE);
    }

    public void clickCurrencyConv() {
        WebDriverWait wait = new WebDriverWait(driver, StringConstants.WAIT_INTERVAL);
        WebElement elemToBeClicked;
        elemToBeClicked = wait.until(ExpectedConditions.elementToBeClickable(CURRENCY_CONVERTER));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemToBeClicked);
    }

    public void eurExchangeBuy() {
        String resultBuy;
        resultBuy = driver.findElement(By.cssSelector("#grt_scrollarea > div.grt_fxCalcResults > div.grt_fxBox.grt_fxResult.previewForward > div:nth-child(1) > p.grt_resultRate")).getText();
        this.EuroBuy = Float.parseFloat(resultBuy.substring(12, 17));
    }

    public void clickChange() {
        WebDriverWait wait = new WebDriverWait(driver, StringConstants.WAIT_INTERVAL);
        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(CHANGE_ACTION));
        toggleButton.click();
    }

    public void eurExchangeSell() {
        String str = driver.findElement(By.cssSelector("#grt_scrollarea > div.grt_fxCalcResults > div.grt_fxBox.grt_fxResult.previewForward > div:nth-child(1) > p.grt_resultRate")).getText();
        str = getExchangeRate(str);
        this.EuroSell = processExchangeRateString(str);
        //this.EuroSell = Float.parseFloat(str.substring(12,17));
    }

    public void closeDriver() {
        driver.close();
    }

    private String getExchangeRate(String str) {
        while (str.isEmpty() && !str.equals(null)) {
            str = driver.findElement(By.cssSelector("#grt_scrollarea > div.grt_fxCalcResults > div.grt_fxBox.grt_fxResult.previewForward > div:nth-child(1) > p.grt_resultRate")).getText();
        }

        return str;
    }

    private Float processExchangeRateString(String str) {
        char[] arr = str.toCharArray();
        int posBegin = 0;
        int posEnd = arr.length;
        Boolean isAfterDecimalPoint = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                for (int k = i; k > 0; k--) {
                    if (arr[k] == ' ') {
                        posBegin = k + 1;
                        isAfterDecimalPoint = true;
                        break;
                    }
                }
            }

            if (isAfterDecimalPoint) {
                if (arr[i] == ' ')
                    posEnd = i;
                break;
            }
        }
        return Float.parseFloat(str.substring(posBegin, posEnd));
    }
}
