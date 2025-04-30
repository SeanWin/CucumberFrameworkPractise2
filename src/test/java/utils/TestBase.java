package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    private WebDriver driver;

    public WebDriver WebDriverManager() throws IOException {
        if (driver == null) {
            // load props
            Properties prop = new Properties();
            try (FileInputStream fis = new FileInputStream("src/test/resources/global.properties")) {
                prop.load(fis);
            }
            String url = prop.getProperty("QAUrl");
            String browser = System.getProperty("browser", prop.getProperty("browser"));

            // detect CI environment for headless
            boolean ci = System.getenv("CI") != null;

            switch (browser.toLowerCase()) {
                case "chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    // CI-friendly flags:
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    if (ci) {
                        options.addArguments("--headless");
                    }
                    // Selenium 4.6+ will auto-download the right chromedriver
                    driver = new ChromeDriver(options);
                }
                case "firefox" -> {
                    FirefoxOptions options = new FirefoxOptions();
                    if (ci) {
                        options.addArguments("-headless");
                    }
                    // Selenium 4.6+ will auto-download geckodriver too
                    driver = new FirefoxDriver(options);
                }
                case "safari" -> {
                    // SafariDriver comes with macOS & must be run non-headless
                    driver = new SafariDriver();
                }
                case "edge" -> {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    if (ci) {
                        options.addArguments("--headless");
                    }
                    driver = new EdgeDriver(options);
                }
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            if (!ci) {
                driver.manage().window().maximize();
            }
            driver.get(url);
        }
        return driver;
    }
}
