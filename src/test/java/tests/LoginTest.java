package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


/*

   // Moved to BaseTest class
   PlaywrightFactory playwrightFactory;
    Page page;
    HomePage homePage;

    @BeforeTest
    void setup(){
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initBrowser();
        homePage = new HomePage(page);
    }

    @AfterTest
    void teardown(){
        page.context().browser().close();
    }
*/


    @Test
    public void TC01_changepassword(){
        passwordResetPage = homePage.clickForgotPasword();
        passwordResetPage.resetPassword("Admin");
    }

    @Test
    public void TC02_login(){
//        homePage.openPage();
        dashBoardPage= homePage.login("Admin", "admin123");
    }

    @Test
    public void TC03_logout(){
        dashBoardPage.logout();
    }
}
