package pages;

import com.microsoft.playwright.Page;

public class DashBoardPage {
    Page page;

    private static final String USER_DROPDOWN = ".oxd-userdropdown-tab";
    private static final String ABOUT = "//a[normalize-space()='About']";
    private static final String SUPPORT = "//a[normalize-space()='Support']";
    private static final String CHANGE_PASSWORD = "//a[normalize-space()='Change Password']";
    private static final String LOGOUT = "//a[normalize-space()='Logout']";

    public DashBoardPage(Page page){
        this.page = page;
    }

    public HomePage logout(){
        page.locator(USER_DROPDOWN).click();
        page.click(LOGOUT);
        return new HomePage(page);
    }
    
}
