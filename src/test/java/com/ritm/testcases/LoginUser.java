package com.ritm.testcases;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.ritm.pages.HandlePopUpsAlerts;
import com.ritm.pages.Login_Page;
import com.ritm.wrapper.ReadingFillo;

public class LoginUser extends Base {

	@Test(dataProvider = "UserCredentials")
	public void Login(String UserName, String Password, String License) throws InterruptedException {
		Login_Page loginpage = new Login_Page(driver);
		HandlePopUpsAlerts popUpsAlerts = new HandlePopUpsAlerts(driver);
		child.log(Status.INFO, "URL is " + baseURL);
		child.log(Status.INFO, "Browser:" + browser);

		loginpage.click_Login();
		loginpage.insertText_username(UserName);
		loginpage.insertText_password(Password);

		/*loginpage.clickElement_loginbutton();
		if (framework.contains("base"))
			driver.switchTo().defaultContent();
		try {
			if (loginpage.isVisible_SecurityGroupText()) {
				loginpage.swithctoframe_selectGroupFrame();
				loginpage.selectDropdown_securityRole(SecurityRole);
				loginpage.clickElement_roleselection();
			}
		} catch (Exception e) {
			System.out.println("No Security Group");
		}
		try {
			if (SecurityRole.equalsIgnoreCase("Loan Officer")) {
				popUpsAlerts.clickElement_PopUpOKbutton();
			} else {
				popUpsAlerts.handleQueryFilterTokens();
			}
		} catch (Exception e) {
			System.out.println("No Popup");
		}*/

	}

	@DataProvider(name = "UserCredentials")
	public Object[][] getDataFromDataprovider(ITestContext context) throws FilloException {
		Object[][] usercredentials = null;
		String SheetName = context.getCurrentXmlTest().getParameter("SheetName");
		String TC_id = context.getCurrentXmlTest().getParameter("TC_id");
		String test = context.getName();
		String username = "";
		String password = "";
		String license = "";
		System.out.println(test);
		if (test.contains("Login")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "UserName");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "License");
		} else if (test.contains("Admin")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Administrator");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "AdminPassword");
		} else if (test.contains("Secondary")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "SecondaryUser");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
		} else if (test.contains("Processing")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Processor");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "ProcessorRole");
		} else if (test.contains("COC")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "COC");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "COCRole");
		} else if (test.contains("Package")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "cdtUser");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "cdtUserRole");
		} else if (test.contains("Underwriting")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Underwriter");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "UnderwriterRole");
		} else if (test.contains("Closing") || test.contains("Funding")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Closer");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "CloserRole");
		} else if (test.contains("Postclosing")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "PostCloser");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "PostCloserRole");
		} else if (test.contains("HMDA")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "HMDAReviewer");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "HMDAReviewerRole");
		} else if (test.contains("Security_Profile")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "SecurityProfile");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "OriginatorRole");
		} else if (test.contains("RegionalOpsManager")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "RegionalOpsManager");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
		} else if (test.contains("processingsupervisor")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Processor");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "ProcessingSupervisor");
		} else if (test.contains("Divisional Ops Mgr")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Specialist");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "Divisional Ops Mgr");
		} else if (test.contains("closinglead")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "ClosingLead");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
		} else if (test.contains("Closing Manager")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "Closer");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "ClosingManager");
		} else if (test.contains("MLA")) {
			username = ReadingFillo.Testdatavalue(SheetName, TC_id, "MLA_Login");
			password = ReadingFillo.Testdatavalue(SheetName, TC_id, "Password");
			license = ReadingFillo.Testdatavalue(SheetName, TC_id, "Loan Consultant");
		}

		usercredentials = new Object[][] {

				{ username, password, license, } };

		return usercredentials;
	}

}
