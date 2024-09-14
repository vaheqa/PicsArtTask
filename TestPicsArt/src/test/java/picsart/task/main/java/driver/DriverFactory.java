package picsart.task.main.java.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver(int width, int height) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=" + width + "," + height);
        return new ChromeDriver(options);
    }
}
