import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MigrosBank;
import page.SwissNationalBank;
import page.UBS;
import strategies.Chrome;
import utils.Driver;

public class TestExchangeRates {

    private static ExtentReports report;
    private static ExtentTest test;
    private static WebDriver webDriver;
    private static MigrosBank migrosBank;
    private static SwissNationalBank swissNationalBank;
    private static UBS ubs;

    @BeforeClass
    public static void instantiateReport() {
        Driver driver = new Driver();
        Chrome chrome = new Chrome();
        webDriver = driver.instantiate(chrome);
        report = new ExtentReports("TestReport.html");
    }

    @Test
    public void testGetExchangeRateMigros() {
        migrosBank = new MigrosBank();
        migrosBank.setDriver(webDriver);
        migrosBank.openWebsite();
        test = report.startTest("Testing the exchange rate retrieval from Migros Bank website");
        migrosBank.openWebsite();
        test.log(LogStatus.PASS, "Migros Bank website has been opened.");
        migrosBank.eurExhange();
        test.log(LogStatus.PASS, "The exchange rate is visible and has been retrieved.");
        test.log(LogStatus.PASS, "Testing has finished successfully.");
    }

    @Test
    public void testGetExchangeRateUBS() {
        ubs = new UBS();
        ubs.setDriver(webDriver);
        test = report.startTest("Testing the exchange rate retrieval from UBS Bank website");
        ubs.openWebsite();
        test.log(LogStatus.PASS, "UBS Bank website has been opened.");
        ubs.clickCurrencyConv();
        ubs.eurExchangeBuy();
        test.log(LogStatus.PASS, "The exchange rate is visible and has been retrieved.");
        ubs.clickChange();
        try {
            ubs.eurExchangeSell();
            test.log(LogStatus.PASS, "The exchange rate is visible and has been retrieved.");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "The exchange rate is not visible.");
            assert (e.fillInStackTrace() == null);
        }
        test.log(LogStatus.PASS, "Testing has finished successfully.");
    }

    @Test
    public void testGetExchangeRateSwiss() {
        swissNationalBank = new SwissNationalBank();
        swissNationalBank.setDriver(webDriver);
        test = report.startTest("Testing the exchange rate retrieval from Swiss National Bank website");
        swissNationalBank.openWebsite();
        test.log(LogStatus.PASS, "Swiss National Bank website has been opened.");
        swissNationalBank.clickExchangeRates();
        swissNationalBank.eurExchange();
        test.log(LogStatus.PASS, "The exchange rate is visible and has been retrieved.");
        test.log(LogStatus.PASS, "Testing has finished successfully.");
    }

    @Test
    public void testCalculateRates() {
        test = report.startTest("Calculating the exchange rates for both banks");
        test.log(LogStatus.INFO, "Migros Bank exchange rate is " + String.format("%.02f", ((swissNationalBank.getEurExchange() / migrosBank.getEurBuy() * 100) - 100)) + "% higher than the reference rate");
        test.log(LogStatus.INFO, "UBS Bank exchange rate is " + String.format("%.02f", ((swissNationalBank.getEurExchange() / ubs.getEuroBuy() * 100) - 100)) + "% higher than the reference rate");
        test.log(LogStatus.INFO, "Migros Bank sell 1 EUR for " + migrosBank.getEurSell() + " CHF");
        test.log(LogStatus.INFO, "UBS Bank sell 1 EUR for " + ubs.getEuroSell() + " CHF");
    }

    @AfterClass
    public static void closeReport() {
        report.endTest(test);
        report.flush();
        webDriver.close();
    }

}


