package factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {
    /*
    // not needed because of thread local implementation
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    */
//    Properties properties = null; // initialized at class level to use properties for all the methods once initiated

    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static Playwright getPlaywrightThreadLocal() {
        return playwrightThreadLocal.get();
    }

    public static BrowserContext getBrowserContextThreadLocal() {
        return browserContextThreadLocal.get();
    }

    public static Browser getBrowserThreadLocal() {
        return browserThreadLocal.get();
    }

    public static Page getPageThreadLocal() {
        return pageThreadLocal.get();
    }

    public Page initBrowser(Properties properties) {

        boolean headless = Boolean.parseBoolean(properties.getProperty("headless"));
//        playwright = Playwright.create();
        playwrightThreadLocal.set(Playwright.create());
        System.out.println("Browser: " + properties.getProperty("browser"));
        switch (properties.getProperty("browser").toLowerCase()) {
            case "chromium":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                browserThreadLocal.set(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "chrome":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome"));
                browserThreadLocal.set(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome")));
                break;
            case "firefox":
//                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                browserThreadLocal.set(getPlaywrightThreadLocal().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "safari":
//                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
                browserThreadLocal.set(getPlaywrightThreadLocal().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "msedge":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless));
                browserThreadLocal.set(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless)));
                break;
            default:
                System.out.println("Chrome browser is selected by default");
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome"));
                browserThreadLocal.set(getPlaywrightThreadLocal().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless).setChannel("chrome")));
                break;
        }
//        browserContext = browser.newContext();
        browserContextThreadLocal.set(getBrowserThreadLocal().newContext());
//        page = browserContext.newPage();
        pageThreadLocal.set(getBrowserContextThreadLocal().newPage());
//        page.navigate(properties.getProperty("url"));
        getPageThreadLocal().navigate(properties.getProperty("url"));
//        return page;
        return getPageThreadLocal();
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

    public static String takeScreenshot() {
        // made some changes to make work with my machine
        String path = System.getProperty("user.dir");
        String fileName = "/screenshots/" + System.currentTimeMillis() + ".png";
        getPageThreadLocal().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path+fileName))
                .setFullPage(true));
        return fileName;
    }
}
