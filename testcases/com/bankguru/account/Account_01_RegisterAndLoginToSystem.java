package com.bankguru.account;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_01_RegisterAndLoginToSystem {
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
	  driver.get("http://demo.guru99.com/v4/index.php");
	  loginURL = driver.getCurrentUrl();
	  
	  driver.findElement(By.xpath("//a[contains(text(),'here')]")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  userID = driver.findElement(By.xpath("//td[contains(text(),'User ID :')]/following-sibling::td")).getText();
	  pass = driver.findElement(By.xpath("//td[contains(text(),'Password :')]/following-sibling::td")).getText();
	  
	  System.out.println("UserID = " + userID);
	  System.out.println("Pass = " + pass);
	  
  }
//  mngr191883
  @Test
  public void TC_02_LoginWithAboveInformation() {
	  driver.get(loginURL);
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
	  
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Manger Id : " + userID + "')]")).isDisplayed());
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
