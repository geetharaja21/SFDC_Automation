package com.selenium.SalesforceE2E;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReusableFunctions {
	
static WebDriver driver;
	
	static ExtentReports report;
	static ExtentTest logger;
	
	static {
		report = new ExtentReports("C:\\Users\\Venkat\\Extent Report\\Reportfile.html", true);
		String path ="C:\\Users\\Venkat\\Extent Report\\Reportfile.html";
		report = new ExtentReports(path);
		
	}
	public static ExtentTest CreateTestScriptReport(String TestScriptName){

		logger=report.startTest(TestScriptName);
		return logger;

	}
	
	/*
	 * Name of the Method:enterText
	 * Brief Description: Enter Text into textbox
	 * Arguments:obj--Web object,text-- text to be entered, objName - Name of the object
	 * Created by: Automation Team
	 * Creation Date:July 18 2019
	 * Last Modified:July 18 2019 
	 */
	

	public static void enterText(WebElement obj,String text,String objName) {
		if(obj.isEnabled()) {
			obj.sendKeys(text);
			System.out.println("Pass: " + text +" is entered in "+objName+"field.." );
			logger.log(LogStatus.PASS,text+"value is entered in"+ objName+"field");
		}else {
			System.out.println(("Fail: " +objName+ "field is not enabled,Please check the application"));
			logger.log(LogStatus.FAIL,objName+"field is not entered, please check the application.." );
		}
	}
	
	/*
	 * Name of the Method:clickButton
	 * Brief Description: click on the object
	 * Arguments:obj -- Web object,objName -- Name of the object
	 * Created by: Automation Team
	 * Creation Date: July 18 2019
	 * Last Modified: July 18 2019 
	 */
	
	public static void clickButton(WebElement obj,String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass: "+objName+ " is clicked");
		    logger.log(LogStatus.PASS, objName+" is clicked");
		}
		else {
			System.out.println("Fail: " +objName+ "field is not enabled,Please check the application");
			logger.log(LogStatus.FAIL, objName+"field is not enabled, please check the application..");
		}
	}
	
	/*
	 * Name of the Method:SelectCheckBox
	 * Brief Description: click on the object
	 * Arguments:obj -- Web object
	 * Created by: Automation Team
	 * Creation Date: July 16 2019
	 * Last Modified: July 16 2019 
	 */

	public static void SelectCheckBox(WebElement obj) {
		try {
			if(obj.isSelected()) {
				System.out.println("CheckBox: "+obj+" is already selected");
				logger.log(LogStatus.PASS,obj+ "is already selected");
			}
			else {
				obj.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to select the checkbox "+obj);
			logger.log(LogStatus.FAIL, "Unable to select the checkbox");
		}
	}
	
	/*
	 * Name of the Method:DeSelectCheckBox
	 * Brief Description: click on the object
	 * Arguments:obj -- Web object
	 * Created by: Automation Team
	 * Creation Date: July 16 2019
	 * Last Modified: July 16 2019 
	 */
	
	public static void DeSelectCheckBox(WebElement obj) {
		try {
			if(obj.isSelected()) {
				obj.click();
			}else {
				System.out.println("CheckBox: "+obj+ "is already deselected");
	        	 logger.log(LogStatus.PASS, obj+ "is already deselected"); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to deselect the checkbox "+obj);
			logger.log(LogStatus.FAIL, "Unable to deselect the checkbox");
		}
	}
	
	/*
	 * Name of the Method:validateTextMessage
	 * Brief Description: Comparing the actual message with the expected Message
	 * Arguments:obj--Web object,text-- text to be expected, objName - Name of the object
	 * Created by: Automation Team
	 * Creation Date:July 16 2019
	 * Last Modified:July 16 2019 
	 */
	
	public static void validateTextMessage(WebElement obj,String expectedMessage,String objName) {
		String actualMessage;
		if(obj.isEnabled()) {
			actualMessage = obj.getText();
			if(actualMessage.equals(expectedMessage)) {
				System.out.println("Pass ..Validation done");
				logger.log(LogStatus.PASS, "Expected Message and Actual message are same");
			}else {
				System.out.println("Fail...Validation failed");
				logger.log(LogStatus.FAIL, "Expected Message and Actual message are different");
			}
		}
	}

	/*
	 * Name of the Method:mouseOver
	 * Brief Description:Incase, we need to click on a sub-menu that is visible only when users do mouse-hover on the main-menu, then we can do it using this function.
	 * Arguments:obj--Web object,WebDriver
	 * Created by: Automation Team
	 * Creation Date:July 16 2019
	 * Last Modified:July 16 2019 
	 */
	public static void mouseOver(WebElement obj,WebDriver driver){

		if(obj.isEnabled()) {
			Actions act=new Actions(driver);
			act.moveToElement(obj).click().build().perform();
			System.out.println("Pass: the object is clicked");
			logger.log(LogStatus.PASS,"the object is clicked");
		}else {
			System.out.println("obj" + " field is not enabled,Please check the application");
			logger.log(LogStatus.FAIL," the object is not clicked");

		}
	}
	
	/*
	 * Name of the method: clickcontacts
	 * Brief Description: clickcontacts in the contact page 
	 * Arguments: Obj-Web Object, string objname
	 * Created by:Automation Team
	 * Creation Date: July 16 2019
	 * Last Modified: July 16 2019
	 */
	public static void clickcontacts(WebElement obj, String objName )
	{
		if(obj.isEnabled()){
			obj.click();
		    System.out.println("Pass: "+ objName+" is clicked");
		    logger.log(LogStatus.PASS, objName+" is clicked");

		}else
		{
			System.out.println("Fail:"+objName+"field is not enabled, please check the application..");
			logger.log(LogStatus.FAIL, objName+"field is not enabled, please check the application..");
		}
	}
	
	/*

	 * Name of the method: selectElementByNameMethod
	 * Brief Description: select the element by name
	 * Arguments: Web Object,Name -- to be selected,objName -- Name of the object
	 * Created by:Automation Team
	 * Creation Date: July 17 2019
	 * Last Modified: July 17 2019
	 */
		
	public static void selectElementByNameMethod(WebElement obj, String Name, String objName) {
		if(obj.isEnabled()) {
			Select select=new Select(obj);
			select.selectByVisibleText(Name);
			System.out.println("Pass:" +Name+"is selected from the list");
			logger.log(LogStatus.PASS, Name+"is selected from the list");
		}else
		{
			System.out.println("Fail:" +objName+"is not present in the list");
			logger.log(LogStatus.FAIL, objName+"is not present in the list");
		}
	}

	public static void selectElementByValueMethod(WebElement element,String value) {
		Select selectitem = new Select(element);
		selectitem.selectByValue(value);
	}

	public static void selectElementByIndexMethod(WebElement element, int index) {
		Select selectitem = new Select(element);
		selectitem.selectByIndex(index);
	}
	
	public static boolean checkAlert_Accept() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.accept();
			return true;

		} catch (Exception e) {

			System.out.println("no alert");
			return false;

		}
	}

	public static boolean checkAlert_Dismiss() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.dismiss();
			return true;

		} catch (Exception e) {

			System.out.println("no alert");
			return false;

		}
	}
	
	public static void switchToNewWindow() {
		Set s = driver.getWindowHandles();
		Iterator itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
	}

	public static void switchToOldWindow() {
		Set s = driver.getWindowHandles();
		Iterator itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w1);
	}
	
	/*
	 * Name of the method: switchtoParentWindow
	 * Brief Description: iframe for switching back to default frame
	 * Arguments:  driver
	 * Created by:Automation Team
	 * Creation Date: July 16 2019
	 * Last Modified: July 16 2019
	 */

	public static void switchToParentWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
		System.out.println("Pass: we can switch to the"+driver+"back to frame");
		logger.log(LogStatus.PASS, "Switched back to default content");
	}
	

	/*
	 * Name of the method: switchFrame based on argument
	 * Brief Description: iframe based on argument
	 * Arguments:  Web object,driver--->driver
	 * Created by:Automation Team
	 * Creation Date: July 16 2019
	 * Last Modified: July 16 2019
	 * */
	
	public static void switchtoFrame(WebElement obj,WebDriver driver) {
		driver.switchTo().frame(obj);
		System.out.println("Pass: We can Switch to the "+obj+"frame");
		logger.log(LogStatus.PASS, "Switched to iframe");
	}
	
	public static void radiobutton_Select(WebElement Radio, String objName) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			System.out.println("Radio button is already checked");
			logger.log(LogStatus.PASS,objName+"is selected");
		} else {
			Radio.click();
			System.out.println("Selected the radio button");
		}
	}
	
	public static void radioButton_Deselect(WebElement Radio) {
		boolean checkstatus;
		checkstatus = Radio.isSelected();
		if (checkstatus == true) {
			Radio.click();
			System.out.println("Radio Button is deselected");
		} else {
			System.out.println("Radio Button was already Deselected");
		}
	}

	
}
