package com.selenium.SalesforceE2E;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.salesforce.maybatch.ReusableMethods;

public class E2ESFDCTest extends ReusableFunctions{

	static WebDriver driver;

	//	public static void main(String[] args) throws InterruptedException {
	//		// TODO Auto-generated method stub
	//		Tc1_login();
	//		logger();
	//	}

	@AfterClass
	public static void logger() {
		ReusableFunctions.report.endTest(logger);
		ReusableFunctions.report.flush();
	}

	@BeforeClass
	public static void initialize_driver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Venkat\\drivers\\chromedriver.exe");

		//Initialize browser
		driver=new ChromeDriver();
	}

	@Test(priority=1)
	public static void launchURL() {

		initialize_driver();
		//Open SalesForce
		driver.get("https://login.salesforce.com/");

		//Maximize browser
		driver.manage().window().maximize();
		System.out.println("Application launched successfully");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		//delete all cookies
		driver.manage().deleteAllCookies();
	}

	@Test(priority=2)
	public static void Tc1_login() throws InterruptedException {

		System.out.println("Welcome to Salesforce Automation");
		CreateTestScriptReport("Tc1_login");
		launchURL();
		//Get Username
		WebElement Uname=driver.findElement(By.xpath("//input[@id='username']"));
		enterText(Uname,"hasita.venkat-xhkn@force.com","UserName");
		System.out.println("Enter username passed successfully");

		WebElement pwd=driver.findElement(By.cssSelector("#password"));
		pwd.clear();
		enterText(pwd,"Confidence21@","PassWord");
		System.out.println("Password entered successfully");
		Thread.sleep(3000);

		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"Login");
		System.out.println("login done successfull");
		Thread.sleep(7000);
		driver.close();
	}

	@Test(priority=3)
	public static void Tc2_LoginError() throws InterruptedException {

		
		CreateTestScriptReport("Tc2_LoginError");
		launchURL();
		//Get Username
		WebElement Uname=driver.findElement(By.xpath("//input[@id='username']"));
		enterText(Uname,"hasita.venkat-xhkn@force.com","UserName");
		System.out.println("Enter username passed successfully");

		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"Login");
		Thread.sleep(3000);
		driver.close();
	}
	
	@Test(priority=4)
	public static void TC3_CheckRememberMe() throws InterruptedException {

		CreateTestScriptReport("Tc2_CheckRememberMe");
		launchURL();

		//Get Username
		WebElement userName=driver.findElement(By.name("username"));
		enterText(userName,"hasita.venkat-xhkn@force.com","UserName");
		System.out.println("Username passed successfully");

		//Enter password
		WebElement passWord=driver.findElement(By.cssSelector("#password"));
		enterText(passWord,"Confidence21@","PassWord");
		System.out.println("Password entered successfully");


		//Check rememberMe button
		WebElement rememberMe=driver.findElement(By.xpath("//input[@id='rememberUn']"));
		SelectCheckBox(rememberMe);
		System.out.println("Remember me checked successfully");

		//Click Login button
		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"Login");
		//loginButton.click();
		System.out.println("Enter login Button passed successfully");

		Thread.sleep(3000);

		WebElement UserMenuChk=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk,driver);
		System.out.println("Checked for UserMenu DropDown");
		Thread.sleep(3000);

		WebElement logOut=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logOut,"Logout");
		System.out.println("Logout done sucessfully");
		Thread.sleep(3000);

		WebElement username=driver.findElement(By.xpath("//span[@id='idcard-identity']"));
		validateTextMessage(username,"hasita.venkat-xhkn@force.com","Username");
		Thread.sleep(3000);
	}

	@Test(priority=5)
	public static void TC4A_ForgotPassword() throws InterruptedException {

		CreateTestScriptReport("TC4A_ForgotPassword");
		launchURL();

		//Get Username
		WebElement userName=driver.findElement(By.name("username"));
		enterText(userName,"hasita.venkat-xhkn@force.com","UserName");
		Thread.sleep(3000);

		WebElement forgotPassword=driver.findElement(By.id("forgot_password_link"));
		clickButton(forgotPassword,"Forgot Password");

		WebElement uName=driver.findElement(By.id("un"));
		enterText(uName,"hasita.venkat-xhkn@force.com","UserName");
		Thread.sleep(3000);

		WebElement Continue=driver.findElement(By.xpath("//input[@id='continue']"));
		clickButton(Continue,"Continue");
		System.out.println("Password Reset Message displayed successfully");
	}

	@Test(priority=6)
	public static void TC4B_ValidateLoginErrorMessage() throws InterruptedException {

		CreateTestScriptReport("TC4B_ValidateLoginErrorMessage");
		launchURL();
		//Get Username
		WebElement userName=driver.findElement(By.name("username"));
		enterText(userName,"123","UserName");
		System.out.println("Username not entered correctly");

		//Enter password
		WebElement passWord=driver.findElement(By.cssSelector("#password"));
		enterText(passWord,"22131","PassWord");
		System.out.println("Password not entered correctly");


		//Click Login button
		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"Login");
		System.out.println("Enter login Button passed successfully");

		Thread.sleep(5000);
		WebElement error=driver.findElement(By.xpath("//div[@id='error']"));
		validateTextMessage(error,"Please check your username and password. If you still can't log in, contact your Salesforce administrator.","error");
		Thread.sleep(3000);
	}
	
	@Test(priority=7)
	public static void MySettings() throws InterruptedException {
		WebElement UserMenuChk1=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk1,driver);
		System.out.println("Checked for UserMenu DropDown");
		Thread.sleep(5000);
		
		WebElement MySettings=driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));
		clickButton(MySettings,"MySettings");
		System.out.println("Clicked on My Settings Option Successfully");
				
		WebElement Personal=driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
		clickButton(Personal,"Personal");
		System.out.println("Personal Link clicked successfully");
				
		WebElement LoginHistory=driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
		clickButton(LoginHistory,"Login History");
		System.out.println("Login History has been clicked Successfully");
		
		
		WebElement DownloadLoginHistory=driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		clickButton(DownloadLoginHistory,"DownloadLoginHistory");
		System.out.println("Downloaded login History successfully");
		Thread.sleep(5000);
		
		WebElement DisplayAndLayout=driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickButton(DisplayAndLayout,"DisplayAndLayout");
		System.out.println("Display and Layout link clicked Successfully");
				
		WebElement CustomizeMyTabs=driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickButton(CustomizeMyTabs,"CustomizeMyTabs");
		System.out.println("Customize My Tabs Link Clicked Successfully");
				
		WebElement CustomApp=driver.findElement(By.xpath("//select[@id='p4']"));
		selectElementByIndexMethod(CustomApp,1);
		//Select custom=new Select(CustomApp);
		//custom.selectByIndex(1);
		
		WebElement AvailableTabs=driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		selectElementByValueMethod(AvailableTabs,"report");
		//Select selectTabs=new Select(AvailableTabs);
		//selectTabs.selectByValue("report");
		
		WebElement Add=driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
		clickButton(Add,"Add");
		System.out.println("Reports field has been added to Selected Tab List Successfully and also added on the links top");
				
		WebElement save1=driver.findElement(By.xpath("//input[@name='save']"));
		clickButton(save1,"Save1");
		Thread.sleep(5000);
		
		WebElement Email=driver.findElement(By.id("EmailSetup_font"));
		clickButton(Email,"Email");
		System.out.println("Email Link Clicked Successfully");
				
		WebElement MyEmailSettings=driver.findElement(By.xpath("//span[@id='EmailSettings_font']"));
		clickButton(MyEmailSettings,"MyEmailSettings");
		Thread.sleep(5000);
		
		WebElement sendThrough=driver.findElement(By.xpath("//input[@id='use_external_email1']"));
		clickButton(sendThrough,"sendThrough");
		
		WebElement EmailName=driver.findElement(By.id("sender_name"));
		EmailName.clear();
		enterText(EmailName,"HemaRaja","Email Name");
		//EmailName.sendKeys("HemaRaja");
		
		WebElement EmailAddr=driver.findElement(By.id("sender_email"));
		EmailAddr.clear();
		enterText(EmailName,"abc123@gmail.com","Email Address");
		//EmailAddr.sendKeys("abc123@gmail.com");
		Thread.sleep(5000);
		
		WebElement Bcc=driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		radiobutton_Select(Bcc,"Click on Bcc");
		
		WebElement SaveButton=driver.findElement(By.xpath("//input[@name='save']"));
		clickButton(SaveButton,"SaveButton");
		System.out.println("Email Settings saved successfully");
		
		WebElement CalendarAndReminders=driver.findElement(By.id("CalendarAndReminders_font"));
		clickButton(CalendarAndReminders,"CalendarAndReminders");
		
		WebElement ActivityReminder=driver.findElement(By.xpath("//span[@id='Reminders_font']"));
		clickButton(ActivityReminder,"ActivityReminder");
		
		WebElement OpenReminder=driver.findElement(By.id("testbtn"));
		clickButton(OpenReminder,"OpenReminder");
		System.out.println("PopUP displayed successfully");
		Thread.sleep(15000);
			
//		WebElement DismissAll=driver.findElement(By.xpath("//input[@id='dismiss_all']"));
//		DismissAll.click();
	}
}
