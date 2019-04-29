package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public void openLoginPage(String urlValue) {
		openURL(driver, urlValue);
	}
	
	public String getLoginURL() {
		return getCurrentURL(driver);
	}
	
	public void inputToUserIDTextbox(String userIDValue) {
		wailtToElementVisiable(driver, LoginPageUI.USER_TEXTBOX);
		sendKeyToEment(driver, LoginPageUI.USER_TEXTBOX, userIDValue);
	}
	
	public void inputToPasswordTextbox(String passwordValue) {
		wailtToElementVisiable(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToEment(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}
	
	public HomePageObject clickToLoginButton() {
		wailtToElementVisiable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToEment(driver, LoginPageUI.LOGIN_BUTTON);
		return PageFactoryManager.getHomePage(driver);
	}
	
	public RegisterPageObject clickToHereLink() {
		wailtToElementVisiable(driver, LoginPageUI.HERE_LINK);
		clickToEment(driver, LoginPageUI.HERE_LINK);
		return PageFactoryManager.getRegisterPage(driver);
	}
}
