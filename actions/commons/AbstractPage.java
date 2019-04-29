package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageUIs.AbstractPageUI;

public class AbstractPage {

	/* WEB BROWSER */
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/* WEB ELEMENT */
	public void clickToEment(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToEment(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void selectItemInHTMLDropdown(WebDriver driver, String locator, String valueInDropdowm) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(valueInDropdowm);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String scrollToXpath, String parentXpath,
			String childXpath, String expectedItem) {

		JavascriptExecutor javaExecutor = (JavascriptExecutor) driver;
		WebDriverWait wailtExplicit = new WebDriverWait(driver, 300);

		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));

		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();

		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		wailtExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));

		for (WebElement child : childList) {
			String textItem = child.getText();
			System.out.println("Text in dropdown : " + textItem);

			if (textItem.equals(expectedItem)) {
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				break;
			}
		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attrubuteName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attrubuteName);
	}

	public String getTextInElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		List<WebElement> elementlist = driver.findElements(By.xpath(locator));
		return elementlist.size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void swichWindowByID(WebDriver driver, String partenID) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (runWindow.equals(partenID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void swichWindowByTitle(WebDriver driver, String pageTitle) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String pageTitleCurrent = driver.getTitle();
			if (pageTitleCurrent.equals(pageTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void swichToIframe(WebDriver driver, String locator) {
		WebElement elementIframe = driver.findElement(By.xpath(locator));
		if(elementIframe.isDisplayed()) {
			driver.switchTo().frame(elementIframe);
		}
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element);
	}
	
	public void dragAndDropToElement(WebDriver driver, String locatorTarget, String locatorSource) {
		WebElement elementTarget = driver.findElement(By.xpath(locatorTarget));
		WebElement elementSource = driver.findElement(By.xpath(locatorSource));
		Actions action = new Actions(driver);
		action.dragAndDrop(elementSource, elementTarget);
	}
	
	public void wailtToElementVisiable(WebDriver driver, String locator) {
		By bylocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
	}
	
	public HomePageObject openHomePage(WebDriver driver) {
		wailtToElementVisiable(driver, AbstractPageUI.HOME_PAGE_LINK);
		clickToEment(driver, AbstractPageUI.HOME_PAGE_LINK);
		return PageFactoryManager.getHomePage(driver);
	}

	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		wailtToElementVisiable(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		clickToEment(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		return PageFactoryManager.getNewCustomerPage(driver);
	}
	
	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		wailtToElementVisiable(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		clickToEment(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		return PageFactoryManager.getNewAccountPage(driver);
	}
	
	public DepositPageObject openDepositPage(WebDriver driver) {
		wailtToElementVisiable(driver, AbstractPageUI.DEPOSIT_LINK);
		clickToEment(driver, AbstractPageUI.DEPOSIT_LINK);
		return PageFactoryManager.getDepositPage(driver);
	}
	
	public FundTransferPageObject openFundTransferPage(WebDriver driver) {
		wailtToElementVisiable(driver, AbstractPageUI.FUND_TRANSFER_LINK);
		clickToEment(driver, AbstractPageUI.FUND_TRANSFER_LINK);
		return PageFactoryManager.getFundTransferPage(driver);
	}
}
