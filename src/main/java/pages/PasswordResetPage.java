package pages;

import com.microsoft.playwright.Page;
import contants.AppConstants;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PasswordResetPage {

    private Page page;
    private static final String USERNAME = "input[placeholder='Username']";
    private static final String RESET_PASSWORD = "//button[normalize-space()='Reset Password']";
    private static final String RESET_PASSWORD_MESSAGE = ".orangehrm-card-container";

    public PasswordResetPage(Page page) {
        this.page = page;
    }

    public void resetPassword(String username){
        page.fill(USERNAME, username);
        page.click(RESET_PASSWORD);
        assertThat(page.locator(RESET_PASSWORD_MESSAGE))
                .containsText(AppConstants.PASSWORD_RESET_MESSAGE);
        page.goBack();
        page.goBack();
    }

}
