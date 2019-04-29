package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driverMapping) {
		driver = driverMapping;
	}
	
	public LoginPageObject openLoginPage(String urlValue) {
		openURL(driver, urlValue);
		return PageFactoryManager.getLoginPage(driver);
	}

	public void inputToEmailIDTextbox(String emailIDValue) {
		wailtToElementVisiable(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendKeyToEment(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, emailIDValue);
	}
	
	public void clickToSubmitButton() {
		wailtToElementVisiable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToEment(driver, RegisterPageUI.SUBMIT_BUTTON);
	}
	
	public String getUserIDText() {
		wailtToElementVisiable(driver, RegisterPageUI.USER_ID_TEXT);
		return getTextInElement(driver, RegisterPageUI.USER_ID_TEXT);
	}
	
	public String getPasswordText() {
		wailtToElementVisiable(driver, RegisterPageUI.PASSWORD_TEXT);
		return getTextInElement(driver, RegisterPageUI.PASSWORD_TEXT);
	}
}
