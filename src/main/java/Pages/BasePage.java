package Pages;

import Context.ThreadContextForScenarios;
import Enums.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    public WebDriver initDriver() {
        if (System.getProperty("os.name").contains("Windows")) {
            WebDriverManager.chromedriver().setup();
            System.out.println("Windows");
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            ThreadContextForScenarios.getInstance();
            ThreadContextForScenarios.setScenarioContext("driver",driver);
            driver.get(Constants.CSVJSONURL.value);
        }
        else{
            System.out.println("UNIX OS");
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.addArguments("no-sandbox");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--headless");
            try {
                this.driver = new ChromeDriver(chromeOptions);
                this.driver.manage().window().maximize();
                this.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                ThreadContextForScenarios.getInstance();
                ThreadContextForScenarios.setScenarioContext("driver",driver);
                driver.get(Constants.CSVJSONURL.value);
            }
            catch(Exception e){
                System.out.println("Issue with instantiating driver on AWS Unix system");
                e.printStackTrace();
            }
        }
        return driver;
    }
}
