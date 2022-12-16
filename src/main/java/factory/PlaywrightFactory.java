package factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
//    Properties properties = null; // initialized at class level to use properties for all the methods once initiated


    public Page initBrowser(Properties properties) {

        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));
        playwright = Playwright.create();
        System.out.println("Browser: " + properties.getProperty("browser"));
        switch (properties.getProperty("browser").toLowerCase()) {
            case "chromium":
                this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome"));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "msedge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless));
                break;
            default:
                System.out.println("Chrome browser is selected by default");
                this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome"));
                break;
        }
        browserContext = this.browser.newContext();
        page = browserContext.newPage();
        page.navigate(properties.getProperty("url"));
        return page;
    }

    public Properties initProperties() {
        Properties properties = null;
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
