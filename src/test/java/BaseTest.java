import io.qameta.allure.Attachment;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    WebDriver driver;
    String BASE_URL = "https://jere237.softr.app/";

    @Before
    public void setUp() {
        MutableCapabilities capabilities = new MutableCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        capabilities.setCapability("browserName", "Chrome");
//        capabilities.setCapability("browserVersion", "latest");
//        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
//        browserstackOptions.put("os", "Windows");
//        browserstackOptions.put("osVersion", "10");
//        browserstackOptions.put("resolution", "1024x768");
//        capabilities.setCapability("bstack:options", browserstackOptions);
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
//        driver = new ChromeDriver(capabilities, options);
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(BASE_URL);
    }

    //    @After
    public void tearDown(){
        driver.quit();
    }

    @Rule
    public TestWatcher screenShotFailure = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            //make screenshot
            makeScreenshotOnFailure();
            driver.close();
            driver.quit();
        }

        @Override
        protected void succeeded(Description description) {
            driver.close();
            driver.quit();
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            makeScreenshotOnFailure();
            driver.close();
            driver.quit();
        }

        @Attachment
        public byte[] makeScreenshotOnFailure(){
            return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        }
    };

}
