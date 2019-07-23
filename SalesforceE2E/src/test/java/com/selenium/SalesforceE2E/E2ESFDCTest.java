package com.selenium.SalesforceE2E;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.salesforce.maybatch.ReusableMethods;
//import com.selenium.SalesforceE2E.ReusableFunctions;

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
		//driver.manage().deleteAllCookies();
	}

	@Test(priority=2)
	public static void Tc1_login() throws InterruptedException {

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
		//driver.close();
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
	public static void Tc3_CheckRememberMe() throws InterruptedException {

		CreateTestScriptReport("Tc3_CheckRememberMe");
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
	public static void Tc4A_ForgotPassword() throws InterruptedException {

		CreateTestScriptReport("Tc4A_ForgotPassword");
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
	public static void Tc4B_ValidateLoginErrorMessage() throws InterruptedException {

		CreateTestScriptReport("Tc4B_ValidateLoginErrorMessage");
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
	public static void Tc08_DeveloperConsole() throws InterruptedException {
		
		CreateTestScriptReport("Tc08_DeveloperConsole");
		launchURL();
		Tc1_login();
		Thread.sleep(5000);
		WebElement UserMenuChk2=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk2,driver);
		Thread.sleep(5000);
		System.out.println("Checked for UserMenu DropDown");
				
		WebElement DeveloperConsole=driver.findElement(By.xpath("//a[@class='debugLogLink menuButtonMenuLink']"));
		clickButton(DeveloperConsole,"DeveloperConsole");
		System.out.println("Clicked on Developer Console Option Successfully");
		Thread.sleep(5000);
		
		String oldWindow=driver.getWindowHandle();
		Set<String> getAllWindows=driver.getWindowHandles();
		String[] getWindow=getAllWindows.toArray(new String[getAllWindows.size()]);
		Thread.sleep(3000);
		driver.switchTo().window(getWindow[1]).close();
	}
	@Test(priority=8)
	public static void Tc07_MySettings() throws InterruptedException {
		
		CreateTestScriptReport("Tc07_MySettings");
		launchURL();
		Tc1_login();
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
		enterText(EmailAddr,"abc123@gmail.com","Email Address");
		//EmailAddr.sendKeys("abc123@gmail.com");
		Thread.sleep(5000);
		
		WebElement Bcc=driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		clickButton(Bcc, "Bcc");
		
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
			
		driver.close();
}
	@Test(priority=9)
	public static void TC06_MyProfile() throws InterruptedException, IOException {
		
		CreateTestScriptReport("TC06_MyProfile");
		launchURL();
		Tc1_login();
		WebElement UserMenuChk1 = driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk1,driver);
		System.out.println("Checked for UserMenu DropDown");
		Thread.sleep(5000);
		
		WebElement MyProfile = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		clickButton(MyProfile,"MyProfile");
		System.out.println("Clicked on MyProfile Option Successfully");
		
		WebElement EditLink = driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
		clickButton(EditLink,"Edit Link");
		System.out.println("Clicked on edit button successfully");
				
		WebElement frame = driver.findElement(By.id("contactInfoContentId"));
		switchtoFrame(frame,driver);
		System.out.println("Switched to frame:");
		Thread.sleep(3000);
		
		WebElement AboutTab = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		mouseOver(AboutTab,driver);
		System.out.println("Clicked on AboutTab Successfully");
				
		WebElement LastName=driver.findElement(By.id("lastName"));
		LastName.clear();
		LastName.sendKeys("RajaSrinivasan");
		
		WebElement saveAll=driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
		clickButton(saveAll,"Save All");
		System.out.println("Save All done successfully");
		Thread.sleep(5000);
		
		switchToParentWindow(driver);
		WebElement post=driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
		clickButton(post,"Post");
		Thread.sleep(5000);
		
		WebElement frame1=driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, publisherRichTextEditor']"));
		switchtoFrame(frame1,driver);
		Thread.sleep(3000);
		
		String text="This is my first Post";
		WebElement postBody=driver.findElement(By.xpath("/html[1]/body[1]"));
		enterText(postBody,text,"Post body");
		//postBody.sendKeys(text);
		
		switchToParentWindow(driver);
		Thread.sleep(5000);
		
		WebElement Share=driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickButton(Share,"Share");
		System.out.println("Posted successfully and the text entrered is displayed");
		Thread.sleep(3000);
		
		WebElement File=driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		clickButton(File,"File");
		Thread.sleep(4000);
		
		WebElement UploadAFile=driver.findElement(By.id("chatterUploadFileAction"));
		clickButton(UploadAFile,"UploadFile");
		
		WebElement ChooseFile=driver.findElement(By.id("chatterFile"));
		clickButton(ChooseFile,"choosefile");
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\Venkat\\Documents\\FileUpload.exe");
		Thread.sleep(5000);
		//ChooseFile.sendKeys("C:\\Users\\Venkat\\eclipse-workspace\\TestNGDemo\\src\\Demo1.java");
				
		WebElement ShareFile=driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickButton(ShareFile,"Share File");
		System.out.println("Selected file posted successfully");
	
		//WebElement profilepic=driver.findElement(By.xpath("//span[@id='displayBadge']"));
		WebElement profile=driver.findElement(By.xpath("//div[@class='photoUploadSection']"));
		mouseOver(profile,driver);
		
		WebElement AddPhoto=driver.findElement(By.id("uploadLink"));
		clickButton(AddPhoto,"Add Photo");
		System.out.println("Add Photo clicked");
		Thread.sleep(3000);
		
		WebElement frame2 =driver.findElement(By.id("uploadPhotoContentId"));
		switchtoFrame(frame2,driver);

		System.out.println("Switched to frame");
		Thread.sleep(7000);
		WebElement choosepic=driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		clickButton(choosepic,"Choose Picture");
		Thread.sleep(10000);
		Runtime.getRuntime().exec("C:\\Users\\Venkat\\Documents\\PicUpload1.exe");
		//choosefile.sendKeys("C:\\Users\\Venkat\\Pictures\\bird.jpg");
		
		Thread.sleep(4000);		
		WebElement SaveButton=driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadBtn']"));
		clickButton(SaveButton,"SaveButton");
		Thread.sleep(30000);
		System.out.println("selected pic successfully");
				
		WebElement save=driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
		clickButton(save,"Save");
		System.out.println("Photo has been uploaded successfully");
		
		driver.close();
	}
	public static void CreateAccount() throws InterruptedException {
		
		CreateTestScriptReport("CreateAccount");
		launchURL();
		Tc1_login();

		WebElement AccountsLink=driver.findElement(By.id("Account_Tab"));
		clickButton(AccountsLink,"AccountsLink");
		System.out.println("Accounts link clicked successfully");
		Thread.sleep(3000);		
		
		WebElement New=driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(New,"New");
		
		WebElement AccountName=driver.findElement(By.id("acc2"));
		enterText(AccountName,"Hema","AccountName");
		//AccountName.sendKeys("Hema");
		Thread.sleep(4000);
		
		WebElement Save=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
		clickButton(Save,"Save");
		System.out.println("New Account Page is displayed with account details successfully");
	}
	
	public static void CreateNewView() throws InterruptedException {
		
		CreateTestScriptReport("CreateNewView");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement AccountsLink=driver.findElement(By.id("Account_Tab"));
		clickButton(AccountsLink,"AccountsLink");
		System.out.println("Accounts link clicked successfully");
		Thread.sleep(3000);
			
		WebElement NewViewLink=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickButton(NewViewLink,"NewViewLink");
		
		WebElement ViewName=driver.findElement(By.id("fname"));
		ViewName.clear();
		enterText(ViewName,"Hema","ViewName");
		//ViewName.sendKeys("Hema");
		Thread.sleep(4000);
		
		WebElement ViewUniqueName=driver.findElement(By.id("devname"));
		ViewUniqueName.clear();
		enterText(ViewUniqueName,"SalesForceDevelopment_Team1","ViewUniqueName");
		//ViewUniqueName.sendKeys("SalesForceDevelopment_Team1");
		
		WebElement Save1=driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		clickButton(Save1,"Save1");
		System.out.println("Newly added view should be displayed successfully in the account view list");
		Thread.sleep(5000);
	}
	
	public static void EditView() throws InterruptedException {
		
		CreateTestScriptReport("EditView");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement AccountsLink=driver.findElement(By.id("Account_Tab"));
		clickButton(AccountsLink,"AccountsLink");
		System.out.println("Accounts link clicked successfully");
			
		WebElement viewDropdown=driver.findElement(By.xpath("//select[@id='fcf']"));
		selectElementByIndexMethod(viewDropdown,2);
//		Select selectView=new Select(viewDropdown);
//		selectView.selectByIndex(2);
		Thread.sleep(5000);
		
		WebElement Edit=driver.findElement(By.xpath("//a[contains(text(),'Edit')]"));
		clickButton(Edit,"Edit");
		System.out.println("View name selected and Edit page has been displayed");
		Thread.sleep(4000);
			
		WebElement viewName=driver.findElement(By.xpath("//input[@id='fname']"));
		enterText(viewName,"Haripriya","viewName");
		//viewName.sendKeys("Haripriya");
		
		WebElement Field=driver.findElement(By.xpath("//select[@id='fcol1']"));
		selectElementByValueMethod(Field,"ACCOUNT.NAME");
		//Select selectField=new Select(Field);
		//selectField.selectByValue("ACCOUNT.NAME");;
		Thread.sleep(3000);
		
		WebElement Operator=driver.findElement(By.xpath("//select[@id='fop1']"));
		selectElementByValueMethod(Operator,"c");
		//Select selectOperator=new Select(Operator);
		//selectOperator.selectByValue("c");;
		
		WebElement Value=driver.findElement(By.xpath("//input[@id='fval1']"));
		Value.clear();
		enterText(Value,"a","Value");
		//Value.sendKeys("a");
		Thread.sleep(3000);
		
		WebElement AvailableFields=driver.findElement(By.xpath("//select[@id='colselector_select_0']"));
		selectElementByNameMethod(AvailableFields,"Last Activity","Name of the field");
		//Select selectAvailFields=new Select(AvailableFields);
		//selectAvailFields.selectByVisibleText("Last Activity");
		
		WebElement Add=driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
		clickButton(Add,"Add");
		Thread.sleep(3000);
		
		WebElement Save2=driver.findElement(By.xpath("//input[@class='btn primary']"));
		clickButton(Save2,"Save2");
		System.out.println("View will have Last activity column added and the data has been displayed successfully");
		Thread.sleep(5000);
	}
	
	public static void MergeAccounts() throws InterruptedException {
		
		CreateTestScriptReport("MergeAccounts");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement AccountsLink=driver.findElement(By.id("Account_Tab"));
		clickButton(AccountsLink,"AccountsLink");
		System.out.println("Accounts link clicked successfully");
			
		WebElement MergeAccounts=driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
		clickButton(MergeAccounts,"MergeAccounts");
		Thread.sleep(3000);
		
		WebElement EnterAccts=driver.findElement(By.id("srch"));
		enterText(EnterAccts,"Hema","EnterAccts");
		//EnterAccts.sendKeys("Hema");
		
		WebElement FindAccts=driver.findElement(By.xpath("//input[@name='srchbutton']"));
		clickButton(FindAccts,"FindAccts");		
		Thread.sleep(3000);
		
		WebElement next=driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='goNext']"));
		clickButton(next,"next");
		System.out.println("Accounts selected successfully and clicked next button");
		Thread.sleep(3000);
				
		WebElement Merge=driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='save']"));
		clickButton(Merge,"Merge");
		Thread.sleep(5000);
		
		checkAlert_Accept();
//		Alert alert=driver.switchTo().alert();
//		alert.accept();
		System.out.println("Accounts merged successfully and pop up displayed");
	}
	
	public static void CreateAccountReport() throws InterruptedException {
		
		CreateTestScriptReport("CreateAccountReport");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement AccountsLink=driver.findElement(By.id("Account_Tab"));
		clickButton(AccountsLink,"AccountsLink");
		System.out.println("Accounts link clicked successfully");
	
		WebElement LastActivity=driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		clickButton(LastActivity,"LastActivity");
		System.out.println("Unsaved Report page is displayed successfully");
				
		WebElement DateField=driver.findElement(By.id("ext-gen20"));
		mouseOver(DateField,driver);		
		Thread.sleep(5000);
		DateField.sendKeys(Keys.ARROW_DOWN);
		DateField.sendKeys(Keys.ENTER);
		
	
		WebElement From=driver.findElement(By.xpath("//img[@id='ext-gen152']"));
		mouseOver(From,driver);
		Thread.sleep(3000);
		//From.sendKeys(Keys.ENTER);
		//.sendKeys(driver.findElement(By.xpath("//button[@id='ext-gen253']")));
		
		WebElement Today=driver.findElement(By.xpath("//table[@id='ext-comp-1112']"));
		clickButton(Today,"Today");
		
		WebElement save2=driver.findElement(By.xpath("//button[@id='ext-gen49']"));
		clickButton(save2,"save2");
		Thread.sleep(4000);
		
		WebElement ReportName=driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
		enterText(ReportName,"Hema","ReportName");
		//ReportName.sendKeys("Hema");
		
		WebElement ReportUniqueName=driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
		enterText(ReportUniqueName,"SFDCReport_Hema1new","ReportUniqueName");
		//ReportUniqueName.sendKeys("SFDCReport_Hema1new");
		Thread.sleep(10000);
		
		WebElement SaveRun=driver.findElement(By.xpath("//button[@id='ext-gen276']"));
		clickButton(SaveRun,"SaveRun");
		System.out.println("Report has been saved successfully");
		Thread.sleep(10000);
		
		System.out.println("Report generated successfully");
	}
	//done here
	public static void Opportunities() throws InterruptedException {
		CreateTestScriptReport("Opportunities");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement Opportunities=driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickButton(Opportunities,"Opportunities");
		Thread.sleep(3000);
		
		WebElement View=driver.findElement(By.xpath("//select[@id='fcf']"));
		mouseOver(View,driver);
		Thread.sleep(3000);
		System.out.println("Dropdown with all Opportunities displayed successfully");
	}
	
	public static void NewOpportunity() throws InterruptedException {
		CreateTestScriptReport("NewOpportunity");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement Opportunities=driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickButton(Opportunities,"Opportunities");
				
		WebElement NewOpp=driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(NewOpp,"NewOpportunities");
		
		WebElement OName=driver.findElement(By.xpath("//input[@id='opp3']"));
		enterText(OName,"Hema Raja","OppName");
		//OName.sendKeys("Hema Raja");
		Thread.sleep(5000);
		
		WebElement AName=driver.findElement(By.xpath("//input[@id='opp4']"));
		enterText(AName,"Hema","AccName");
		//AName.sendKeys("Hema");
		
		WebElement CloseDate=driver.findElement(By.xpath("//input[@id='opp9']"));
		mouseOver(CloseDate,driver);
		
		WebElement Today=driver.findElement(By.xpath("//a[@class='calToday']"));
		clickButton(Today,"Today");
		Thread.sleep(4000);
		
		WebElement Stage=driver.findElement(By.xpath("//select[@id='opp11']"));
		selectElementByIndexMethod(Stage,1);
		//Select selectStage=new Select(Stage);
		//selectStage.selectByIndex(1);
		
		WebElement Probability=driver.findElement(By.xpath("//input[@id='opp12']"));
		Probability.clear();
		enterText(Probability,"10","Probability");
		//Probability.sendKeys("10");
		Thread.sleep(3000);
		
		WebElement PrimaryCampaign=driver.findElement(By.xpath("//input[@id='opp17']"));
		enterText(PrimaryCampaign,"Widgets Webinar(Sample)","PrimaryCampaign");
		//PrimaryCampaign.sendKeys("Widgets Webinar(Sample)");
		Thread.sleep(3000);
		
		WebElement LeadSource=driver.findElement(By.xpath("//select[@id='opp6']"));
		selectElementByIndexMethod(LeadSource,2);
//		Select selectLead=new Select(LeadSource);
//		selectLead.selectByIndex(2);
		
		WebElement SaveOpp=driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		clickButton(SaveOpp,"SaveOpp");
		Thread.sleep(3000);
		System.out.println("New Opportunity page is displayed with details");
	}
	
	public static void Test_OpportunityPipeline_Report() throws InterruptedException {
		CreateTestScriptReport("Test_OpportunityPipeline_Report");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement Opportunities=driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickButton(Opportunities,"Opportunities");
		Thread.sleep(3000);
		
		WebElement OPipeline=driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		clickButton(OPipeline,"Opportunity Pipeline");
		System.out.println("Report Page with Opportunities that are pipelined will be displayed");
		Thread.sleep(3000);
	}
	
	public static void Test_StuckOpportunities_Report() throws InterruptedException {
		CreateTestScriptReport("Test_StuckOpportunities_Report");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement Opportunities=driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickButton(Opportunities,"Opportunities");
		Thread.sleep(3000);
		
		WebElement StuckOpp=driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickButton(StuckOpp,"StuckOpp");
		System.out.println("Report Page with the Opportunities that are Stuck will be displayed.");
	}
	
	public static void Test_QuarterlySummary_Report() throws InterruptedException {
		
		CreateTestScriptReport("Test_QuarterlySummary_Report");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement Opportunities=driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickButton(Opportunities,"Opportunities");
		
		WebElement Interval=driver.findElement(By.xpath("//select[@id='quarter_q']"));
		selectElementByValueMethod(Interval,"curnext3");
//		Select selectInt=new Select(Interval);
//		selectInt.selectByValue("curnext3");
		Thread.sleep(4000);
		
		WebElement Include=driver.findElement(By.xpath("//select[@id='open']"));
		selectElementByValueMethod(Include,"open");
//		Select selectInclude=new Select(Include);
//		selectInclude.selectByValue("open");
		
		WebElement RunReport=driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']"));
		clickButton(RunReport,"RunReport");
		System.out.println("Respective Report page has been displayed successfully");
		Thread.sleep(3000);
		
	}
	
	//done
	
	public static void Leads_Tab() throws InterruptedException {
		CreateTestScriptReport("Leads_Tab");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		
		WebElement LeadsTab=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab,"LeadsTab");
		Thread.sleep(3000);
		System.out.println("Link Navigated to the Leads Home Page Successfully");
	}
	
	public static void Leads_SelectView() throws InterruptedException {

		CreateTestScriptReport("Leads_SelectView");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		WebElement LeadsTab1=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab1,"LeadsTab");
		Thread.sleep(3000);
		System.out.println("Link Navigated to the Leads Home Page Successfully");
		
		WebElement LeadView=driver.findElement(By.xpath("//select[@id='fcf']"));
		mouseOver(LeadView,driver);
		Thread.sleep(3000);
		System.out.println("Dropdown with all Lead contents displayed successfully");
		
		WebElement UserMenuChk3=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk3,driver);
		System.out.println("Checked for UserMenu DropDown");
				
		Thread.sleep(5000);
		
		WebElement logOut=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logOut,"logOut");
		System.out.println("Logout done sucessfully");
				
	}
	
	public static void Default_View() throws InterruptedException {
		
		CreateTestScriptReport("Default_View");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement LeadsTab2=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab2,"LeadsTab");
		Thread.sleep(3000);
		System.out.println("Link Navigated to the Leads Home Page Successfully");
		
		WebElement LeadView=driver.findElement(By.xpath("//select[@id='fcf']"));
		selectElementByIndexMethod(LeadView,2);
		Thread.sleep(3000);
		System.out.println("Today's Lead selected successfully");
		
		WebElement UserMenuChk4=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk4,driver);
		System.out.println("Checked for UserMenu DropDown");
				
		Thread.sleep(5000);
		
		WebElement logOut1=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logOut1,"logOut");
		System.out.println("Logout done sucessfully");
		Thread.sleep(10000);
		
		WebElement pwd1=driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd1,"Metropcs21","Password");
	//	pwd1.sendKeys("Metropcs21");
		Thread.sleep(3000);
		
		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"loginButton");
		Thread.sleep(3000);
		
		WebElement LeadsTab3=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab3,"LeadsTab");
		Thread.sleep(2000);
		
		WebElement Go=driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
		clickButton(Go,"Go");
		System.out.println("Lead View's Page displayed successfully");
		Thread.sleep(3000);
	}
	
	public static void Todays_Lead() throws InterruptedException {
		
		CreateTestScriptReport("Todays_Lead");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement LeadsTab=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab,"LeadsTab");
		Thread.sleep(3000);
		System.out.println("Link Navigated to the Leads Home Page Successfully");
		
		WebElement LeadView=driver.findElement(By.xpath("//select[@id='fcf']"));
		selectElementByIndexMethod(LeadView,2);
		Thread.sleep(3000);
		System.out.println("Today's Lead selected successfully");
		
		WebElement Go1=driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
		clickButton(Go1,"Go");
		System.out.println("Lead View's Page displayed successfully");
		Thread.sleep(3000);
		
		WebElement UserMenuChk5=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk5,driver);
		System.out.println("Checked for UserMenu DropDown");
				
		Thread.sleep(5000);
		
		WebElement logOut2=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logOut2,"logOut");
		System.out.println("Logout done sucessfully");
		Thread.sleep(10000);
		
		driver.quit();
		System.out.println("Application closed successfully");
	}
	
	public static void Check_New_OnLeads() throws InterruptedException {
		CreateTestScriptReport("Check_New_OnLeads");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement LeadsTab=driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
		clickButton(LeadsTab,"LeadsTab");
		Thread.sleep(3000);
		System.out.println("Link Navigated to the Leads Home Page Successfully");
		
		WebElement NewLead=driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(NewLead,"NewLead");
		System.out.println("New Lead Creation page opened successfully");
		Thread.sleep(3000);
		
		WebElement LName=driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
		enterText(LName,"ABCD","Last Name");
		LName.sendKeys("ABCD");
		
		WebElement Company=driver.findElement(By.xpath("//input[@id='lea3']"));
		enterText(Company,"ABCD","Company Name");
		Thread.sleep(3000);
		
		WebElement SaveLead=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
		clickButton(SaveLead,"SaveLead");
		Thread.sleep(4000);
		System.out.println("Newly created lead view page should be opened successfully");
		
	}
	
	public static void Create_New_Contact() throws InterruptedException {
		CreateTestScriptReport("Create_New_Contact");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement NewContact=driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(NewContact,"NewContact");
		System.out.println("New Contact HomePage displayed successfully");
		
		WebElement ContactsLName=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(ContactsLName,"Govind","Contact Name");
		//ContactsLName.sendKeys("Govind");
		
		WebElement AcctName=driver.findElement(By.xpath("//input[@id='con4']"));
		enterText(AcctName,"Hema","AcctName");
		//AcctName.sendKeys("Hema");
		Thread.sleep(3000);
		
		WebElement SaveContact=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
		clickButton(SaveContact,"SaveContact");
		Thread.sleep(3000);
		System.out.println("New Contact created successfully");
	}
	
	public static void Create_NewView_ContactsPage() throws InterruptedException {
		CreateTestScriptReport("Create_NewView_ContactsPage");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab1=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab1,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement CreateNewView=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickButton(CreateNewView,"CreateNewView");
		Thread.sleep(3000);
		System.out.println("New View Page displayed successfully");
		
		WebElement ViewNameCont=driver.findElement(By.xpath("//input[@id='fname']"));
		enterText(ViewNameCont,"Raj","ViewNameCont");
		//ViewNameCont.sendKeys("Raj");
		
		WebElement ViewUName=driver.findElement(By.xpath("//input[@id='devname']"));
		ViewUName.clear();
		enterText(ViewUName,"Varma","ViewUName");
		//ViewUName.sendKeys("Varma");
		Thread.sleep(2000);
		
		WebElement SaveView=driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		clickButton(SaveView,"SaveView");
		Thread.sleep(5000);
		System.out.println("Created View name is displayed in drop down in that page by defalut");
	}
	
	public static void Check_Recent_Contacts() throws InterruptedException {
		CreateTestScriptReport("Check_Recent_Contacts");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);

		WebElement ContactsTab2=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab2,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement ContactsMode=driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		selectElementByValueMethod(ContactsMode,"2");
		Thread.sleep(4000);
		System.out.println("Recently created contacts should be displayed");
	}
	
	public static void Check_MyContacts_View() throws InterruptedException {
		
		CreateTestScriptReport("Check_MyContacts_View");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab3=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab3,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement ContactView=driver.findElement(By.xpath("//select[@id='fcf']"));
		selectElementByNameMethod(ContactView,"My Contacts","view contacts");
		//Select selcontact=new Select(ContactView);
		//selcontact.selectByVisibleText("My Contacts");
		Thread.sleep(3000);
		System.out.println("My contacts view displayed successfully");
	}
	
	public static void View_A_Contact() throws InterruptedException {
		CreateTestScriptReport("View_A_Contact");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab4=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab4,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement ContactsName=driver.findElement(By.xpath("//tr[contains(@class,'dataRow even first')]//a[contains(text(),'Govind')]"));
		clickButton(ContactsName,"ContactsName");
		Thread.sleep(2000);
		System.out.println("Related Contact page displayed successfully");
	}
	
	public static void Check_Error_Message() throws InterruptedException {
		CreateTestScriptReport("Check_Error_Message");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab5=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab5,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement CreateNewView1=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickButton(CreateNewView1,"CreateNewView");
		Thread.sleep(3000);
		System.out.println("New View Page displayed successfully");
		
		WebElement ViewUName1=driver.findElement(By.xpath("//input[@id='devname']"));
		ViewUName1.clear();
		enterText(ViewUName1,"EFGH","ViewUName");
		//ViewUName1.sendKeys("EFGH");
		Thread.sleep(2000);
		
		WebElement SaveView1=driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		clickButton(SaveView1,"SaveView");
		Thread.sleep(5000);
		System.out.println("Error message is appeared under the View Name field.The Error message appears as \"Error: You must enter a value\"");
	}
	
	public static void Check_Cancel_Button() throws InterruptedException {
		
		CreateTestScriptReport("Check_Cancel_Button");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab6=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab6,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement CreateNewView2=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickButton(CreateNewView2,"CreateNewView");
		Thread.sleep(3000);
		System.out.println("New View Page displayed successfully");
		
		WebElement ViewNameCont2=driver.findElement(By.xpath("//input[@id='fname']"));
		ViewNameCont2.sendKeys("ABCD");
		
		WebElement ViewUName2=driver.findElement(By.xpath("//input[@id='devname']"));
		ViewUName2.clear();
		Thread.sleep(2000);
		enterText(ViewUName2,"EFGH","ViewUName");
		//ViewUName2.sendKeys("EFGH");
		Thread.sleep(2000);
		System.out.println("View name and Unique name entered successfully");
		
		WebElement ViewCancel=driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='cancel']"));
		clickButton(ViewCancel,"ViewCancel");
		System.out.println("Contacts Home page is displayed and the View ABCD should not be created.");
	}
	
	public static void Check_SaveNew_ContactPage() throws InterruptedException {
		
		CreateTestScriptReport("Check_SaveNew_ContactPage");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement ContactsTab7=driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"));
		clickButton(ContactsTab7,"ContactsTab");
		Thread.sleep(2000);
		System.out.println("Contacts Home page displayed successfully");
		
		WebElement NewContact1=driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(NewContact1,"NewContact");
		System.out.println("New Contact HomePage displayed successfully");
		
		WebElement ContactsLName1=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(ContactsLName1,"Indian","ContactsLName");
		//ContactsLName1.sendKeys("Indian");
		
		WebElement AcctName1=driver.findElement(By.xpath("//input[@id='con4']"));
		enterText(AcctName1,"Global Media","AcctName");
		//AcctName1.sendKeys("Global Media");
		Thread.sleep(3000);
		
		WebElement SaveNewContact=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save_new']"));
		clickButton(SaveNewContact,"SaveNewContact");
		Thread.sleep(2000);
		System.out.println("New contact is created. Contact Edit:New Contact Page is dispalyed");
		
	}
	
	public static void Verify_Fname_Lname_HomePage() throws InterruptedException {
		
		CreateTestScriptReport("Verify_Fname_Lname_HomePage");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement HomeTab=driver.findElement(By.xpath("//a[@class='brandPrimaryFgr']"));
		clickButton(HomeTab,"HomeTab");
		System.out.println("Home Page displayed successfully");
		
		WebElement NameLink=driver.findElement(By.xpath("//h1[@class='currentStatusUserName']//a[contains(text(),'Hema RajaSrinivasan')]"));
		clickButton(NameLink,"NameLink");
		Thread.sleep(3000);
		System.out.println("The page displayed should be same as MyProfile page");
	}
	
	public static void Verify_Edited_LastName() throws InterruptedException {
		
		CreateTestScriptReport("Verify_Edited_LastName");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement HomeTab1=driver.findElement(By.xpath("//a[@class='brandPrimaryFgr']"));
		clickButton(HomeTab1,"HomeTab1");
		System.out.println("Home Page displayed successfully");
		
		WebElement NameLink1=driver.findElement(By.xpath("//h1[@class='currentStatusUserName']//a[contains(text(),'Hema RajaSrinivasan')]"));
		clickButton(NameLink1,"NameLink");
		Thread.sleep(3000);
		
		WebElement EditProfileIcon=driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
		clickButton(EditProfileIcon,"EditProfileIcon");
		Thread.sleep(3000);
		System.out.println("The 'Edit Profile' popup should be displayed with the 'Contact' tab selected.");
		
		driver.switchTo().frame("contactInfoContentId");
		System.out.println("Switched to frame:");
		Thread.sleep(3000);
		
		WebElement AboutTab1 = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		mouseOver(AboutTab1,driver);
		System.out.println("Clicked on AboutTab Successfully");
		
		WebElement LastName1=driver.findElement(By.id("lastName"));
		LastName1.clear();
		enterText(LastName1,"ABCD","LastName");
		//LastName1.sendKeys("ABCD");
		
		WebElement saveAll1=driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
		clickButton(saveAll1,"saveAll");
		System.out.println("Save All done and Last Name updated successfully");
		Thread.sleep(5000);
	}
	
	public static void Verify_Tab_Customization() throws InterruptedException {
		
		CreateTestScriptReport("Verify_Tab_Customization");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement PlusTab=driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		clickButton(PlusTab,"PlusTab");
		
		WebElement CustomizeMyTab1=driver.findElement(By.xpath("//input[@name='customize']"));
		clickButton(CustomizeMyTab1,"CustomizeMyTab1");
		Thread.sleep(3000);
		System.out.println("'The 'Customize My Tabs' page should be displayed.");
		
		WebElement SelectedTabs=driver.findElement(By.xpath("//select[@id='duel_select_1']"));
		Select selectTabs=new Select(SelectedTabs);
		selectTabs.selectByValue("Chatter");
		
		WebElement Remove=driver.findElement(By.xpath("//img[@class='leftArrowIcon']"));
		clickButton(Remove,"Remove");
		Thread.sleep(2000);
		System.out.println("The Selected Tab should be moved to the 'Available Tabs' section.");
		
		WebElement Save3=driver.findElement(By.xpath("//input[@name='save']"));
		clickButton(Save3,"Save3");
		Thread.sleep(2000);
		
		WebElement UserMenuChk6=driver.findElement(By.xpath("//div[@id='userNav-arrow']"));
		mouseOver(UserMenuChk6,driver);
		System.out.println("Checked for UserMenu DropDown");
				
		Thread.sleep(5000);
		
		WebElement logOut3=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickButton(logOut3,"logOut");
		System.out.println("Logout done sucessfully");
		Thread.sleep(5000);
		
		WebElement pwd2=driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd2,"Metropcs21","password");
		//pwd2.sendKeys("Metropcs21");
		Thread.sleep(3000);
		
		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"loginButton");
		Thread.sleep(3000);
	}
	
	public static void Block_An_Event_Calendar() throws InterruptedException {
		
		CreateTestScriptReport("Block_An_Event_Calendar");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement HomeTab2=driver.findElement(By.xpath("//a[@class='brandPrimaryFgr']"));
		clickButton(HomeTab2,"HomeTab");
		System.out.println("Home Page displayed successfully");
		
		WebElement CurrentDate=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[1]/span[2]/a[1]"));
		clickButton(CurrentDate,"CurrentDate");
		Thread.sleep(3000);
		
		WebElement ClickLink=driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
		clickButton(ClickLink,"ClickLink");
		Thread.sleep(2000);
		
		WebElement SubjComboIcon=driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		clickButton(SubjComboIcon,"SubjComboIcon");
		
		String MainWindow=driver.getWindowHandle();
		
		//To get window Handle of all Opened Windows
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1=s1.iterator();
		
		while(i1.hasNext()) {
			String ChildWindow=i1.next();
			
			if(!MainWindow.equalsIgnoreCase(ChildWindow)) {
				//Switching to childWindow
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.xpath("//a[contains(text(),'Other')]")).click();
				}
		}
		//Switching to Parent Window
		
		driver.switchTo().window(MainWindow);	
		Thread.sleep(2000);
		
		WebElement EndTime=driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		mouseOver(EndTime,driver);
		
		WebElement selectEndTime=driver.findElement(By.xpath("//div[@id='timePickerItem_42']"));
		clickButton(selectEndTime,"selectEndTime");
		Thread.sleep(3000);
		
		WebElement SaveTime=driver.findElement(By.xpath("//div[contains(@class,'pbBottomButtons')]//input[1]"));
		clickButton(SaveTime,"SaveTime");
		Thread.sleep(3000);
		System.out.println("The 'Calendar for FirstName LastName' page should be displayed with 'Other' event in the time slot 8:00PM to 9:00PM, as a link.");
		
	}
	
	public static void Block_Event_Calendar_With_Recurrence() throws InterruptedException {
		
		CreateTestScriptReport("Block_Event_Calendar_With_Recurrence");
		launchURL();
		Tc1_login();
		Thread.sleep(4000);
		
		WebElement HomeTab3=driver.findElement(By.xpath("//a[@class='brandPrimaryFgr']"));
		clickButton(HomeTab3,"HomeTab");
		System.out.println("Home Page displayed successfully");
		
		WebElement CurrentDate1=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[1]/div[1]/div[1]/div[1]/span[2]/a[1]"));
		clickButton(CurrentDate1,"CurrentDate1");
		Thread.sleep(3000);
		
		WebElement Click4PMLink=driver.findElement(By.xpath("//a[contains(text(),'4:00 PM')]"));
		clickButton(Click4PMLink,"Click4PMLink");
		Thread.sleep(2000);
		
		WebElement SubjComboIcon=driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		clickButton(SubjComboIcon,"SubjComboIcon");
		
		String MainWindow1=driver.getWindowHandle();
		
		//To get window Handle of all Opened Windows
		Set<String> s2=driver.getWindowHandles();
		Iterator<String> i2=s2.iterator();
		
		while(i2.hasNext()) {
			String ChildWindow1=i2.next();
			
			if(!MainWindow1.equalsIgnoreCase(ChildWindow1)) {
				//Switching to childWindow
				driver.switchTo().window(ChildWindow1);
				driver.findElement(By.xpath("//a[contains(text(),'Other')]")).click();
				}
		}
		//Switching to Parent Window
		
		driver.switchTo().window(MainWindow1);	
		Thread.sleep(2000);
		System.out.println("Other should be entered in the Subject field");
		
		WebElement EndTime1=driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		mouseOver(EndTime1,driver);
		
		WebElement selectEndTime1=driver.findElement(By.xpath("//div[@id='timePickerItem_38']"));
		clickButton(selectEndTime1,"selectEndTime");
		Thread.sleep(3000);
		
		WebElement CheckRecurrence=driver.findElement(By.xpath("//input[@id='IsRecurrence']"));
		clickButton(CheckRecurrence,"CheckRecurrence");
		Thread.sleep(2000);
		
		WebElement Weekly=driver.findElement(By.xpath("//input[@id='rectypeftw']"));
		clickButton(Weekly,"Weekly");
		
		WebElement RecurrenceEnd=driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
		mouseOver(RecurrenceEnd,driver);
		
		WebElement DateAfterTwoWeeks=driver.findElement(By.xpath("//td[contains(text(),'17')]"));
		clickButton(DateAfterTwoWeeks,"DateAfterTwoWeeks");
		Thread.sleep(2000);
		System.out.println("The 'End Date' should be selected and the calendar should be closed.");
		
		WebElement SaveTime1=driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		clickButton(SaveTime1,"SaveTime");
		Thread.sleep(4000);
		
		WebElement MonthView=driver.findElement(By.xpath("//img[@class='monthViewIcon']"));
		clickButton(MonthView,"MonthView");
		Thread.sleep(7000);
		System.out.println("Month View' page should be displayed and 'Other' event blocked as a link.");
		
		driver.quit();
		
	}
	
	public static void Verify_Welcome_Page() throws InterruptedException {
		CreateTestScriptReport("Verify_Welcome_Page");
				
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Venkat\\drivers\\chromedriver.exe");
		
		//Initialize browser
		driver=new ChromeDriver();
		//Open SalesForce
		driver.get("https://login.salesforce.com/");
		
		//Maximize browser
		driver.manage().window().maximize();
		System.out.println("Application launched successfully");
				
		//delete all cookies
		driver.manage().deleteAllCookies();
		
		//Get Username
		WebElement userNamenew=driver.findElement(By.name("username"));
		enterText(userNamenew,"Metropcs21","username new");
		//userNamenew.sendKeys("bhagatlingesh@gmail.com");
		System.out.println("Enter username passed successfully");
			
		//Enter password
		WebElement passWordnew=driver.findElement(By.cssSelector("#password"));
		enterText(passWordnew,"Metropcs21","password new");
		passWordnew.sendKeys("lexkum143!");
		
		WebElement loginButton=driver.findElement(By.xpath("//input[@id='Login']"));
		clickButton(loginButton,"loginButton");
		System.out.println("Enter login Button passed successfully");
		
		Thread.sleep(5000);
		System.out.println("Test done");
	}
}
