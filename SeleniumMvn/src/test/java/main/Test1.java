package main;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

public class Test1 {
	
	//Create firfoxd driver's instance 
	WebDriver driver = new FirefoxDriver();
	
@Test //Tests google calculator 
public void googleCalculator() throws IOException{
	try{ 
		//Set implicit wait of 10 seconds 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		//Launch google 
		driver.get("http://www.google.com"); 
		//Write 2+2 in google textbox 
		WebElement googleTextBox = driver.findElement(By.id("gbqfq"));
		googleTextBox.sendKeys("2+2"); 
		//Click on searchButton 
		WebElement searchButton = driver.findElement(By.id("gbqfb")); 
		searchButton.click(); 
		//Get result from calculator 
		WebElement calculatorTextBox = driver.findElement(By.id("cwos")); 
		String result = calculatorTextBox.getText(); 
		//Intentionaly checking for wrong calculation of 2+2=5 in order to take screenshot for faling test 
		Assert.assertEquals(result, "5");
	}
	catch(Exception e){ Assert.fail(); 
	//To fail test in case of any element identification failure	
	} } 

@AfterMethod
public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
if (testResult.getStatus() == ITestResult.FAILURE) { 
System.out.println(testResult.getStatus()); 

File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
String fileName = new SimpleDateFormat("MM-dd-yyyy_kkmmss_SSS").format(new Date());
FileUtils.copyFile(scrFile, new File("C:\\screens\\screenshot_"+fileName+".jpg")); 

}
}
}