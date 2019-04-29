package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterLogin_Level2_AbstractPage_MultiBrowser extends AbstractPage{
	WebDriver driver;
	private String userID, pass;
	private String email, loginURL;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  email = "hoanglan" + RandomEmail() + "@gmail.com";
	  System.out.println(email);
  }
  
  @Test
  public void TC_01_RegisterToSystem() {
	  openURL(driver, "http://demo.guru99.com/v4/index.php");
	  loginURL = getCurrentURL(driver);

	  clickToEment(driver, "//a[contains(text(),'here')]");
	  Assert.assertTrue(isControlDisplayed(driver, "//input[@name='emailid']"));
	  sendKeyToEment(driver, "//input[@name='emailid']", email);
	  clickToEment(driver, "//input[@name='btnLogin']");
	  
	  userID = getTextInElement(driver, "//td[contains(text(),'User ID :')]/following-sibling::td");
	  pass = getTextInElement(driver, "//td[contains(text(),'Password :')]/following-sibling::td");
	  
	  System.out.println("UserID = " + userID);
	  System.out.println("Pass = " + pass);
	  
  }
//  mngr191883
  @Test
  public void TC_02_LoginWithAboveInformation() {
	  openURL(driver, loginURL);
	  
	  sendKeyToEment(driver, "//input[@name='uid']", userID);
	  sendKeyToEment(driver, "//input[@name='password']", pass);
	  clickToEment(driver, "//input[@name='btnLogin']");
	  
	  Assert.assertTrue(isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
	  Assert.assertTrue(isControlDisplayed(driver, "//td[contains(text(),'Manger Id : " + userID + "')]"));
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int RandomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
	  
  }

}
