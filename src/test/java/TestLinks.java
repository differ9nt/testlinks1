import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestLinks {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://112.ua/1");
    }

    @Test
    public void linksErrors() {
        try {
           BufferedWriter fw = new BufferedWriter(new FileWriter("log.txt", true));
           Date dateNow = new Date();
            SimpleDateFormat dateNowFormat = new SimpleDateFormat("dd.MM.yyyy ' ' hh:mm:ss a zzz");


            if ((driver.getTitle().contains("404")) || (driver.getTitle().contains("500"))) {



                Logs logs = driver.manage().logs();
                LogEntries logEntries = logs.get(LogType.BROWSER);

                for (LogEntry logEntry : logEntries) {
                    System.out.println(dateNowFormat.format(dateNow) + " " + logEntry.getMessage());
                    fw.append(dateNowFormat.format(dateNow) + " " + logEntry.getMessage());
                    fw.append('\n');

                }
                fw.append('\n');


            }


            WebElement link2 = driver.findElement(By.xpath("//a[@class='icon-live']"));
            link2.click();

            if ((driver.getTitle().contains("404")) || (driver.getTitle().contains("500"))) {

                Logs logs1 = driver.manage().logs();
                LogEntries logEntries1 = logs1.get(LogType.BROWSER);

                for (LogEntry logEntry : logEntries1) {
                    System.out.println(dateNowFormat.format(dateNow) + " " + logEntry.getMessage());
                    fw.append(dateNowFormat.format(dateNow) + " " + logEntry.getMessage());
                    fw.append('\n');

                }
                fw.append('\n');

           }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
