package pages;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;

public class HomePage {
    private Page page;

    // 1. Locators
    private static final String USERNAME = "input[placeholder='Username']";
    private static final String PASSWORD = "input[placeholder='Password']";
    private static final String LOGIN = "button[type='submit']";
    private static final String FORGOT_PASSWORD = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']";

    // 2. Page Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    // 3. Page Actions
    public DashBoardPage login(String username, String password) {
//        page.locator(USERNAME).clear();
        page.locator(USERNAME).fill(username);
//        page.locator(PASSWORD).clear();
        page.locator(PASSWORD).fill(password);
        page.locator(LOGIN).click();
        return new DashBoardPage(page); //Page chaining
    }

    public PasswordResetPage clickForgotPasword() {
        page.click(FORGOT_PASSWORD);
        return new PasswordResetPage(page); //Page chaining
    }

    public HomePage openPage(){
        page.navigate(new PlaywrightFactory().initProperties().getProperty("url"));
        return new HomePage(page);
    }


}
