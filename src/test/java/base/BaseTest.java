package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.DashBoardPage;
import pages.HomePage;
import pages.PasswordResetPage;

import java.util.Properties;

public class BaseTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    protected HomePage homePage; //make this protected unless this won't be accessible by test class
    protected PasswordResetPage passwordResetPage;
    protected DashBoardPage dashBoardPage;
    Properties properties;

    @BeforeTest
    void setup(){
        System.out.println("before test");
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initProperties(); //should be done before initBrowser()
        page = playwrightFactory.initBrowser(properties);
        homePage = new HomePage(page);
    }

    @AfterTest
    void teardown(){
        System.out.println("after test");
        page.context().browser().close();
    }
}
