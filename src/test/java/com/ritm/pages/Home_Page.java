package com.ritm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.testng.annotations.Parameters;

import com.ritm.wrapper.CommonFunctions;
@Parameters({"Protocol","Environment"})
public class Home_Page extends CommonFunctions   {

	public Home_Page(WebDriver driver) {
		super();
    }
	
	 @FindBy(how=How.XPATH,using="//*[contains(@class,'fa-forward')]")
     WebElement ForewardBtn;
	 
	 @FindBy(how=How.XPATH,using="//div[@title='Resources']")
     WebElement ResourcesTab;
	 
	 @FindBy(how=How.XPATH,using="//div[@title='Download']")
     WebElement DownloadTab;
	 
	 @FindBy(how=How.XPATH,using="//div[@title='Documentation']")
     WebElement DocumentationTab;
	 
	 public void  click_ForewardBtn() throws InterruptedException
	    {
	    	CommonFunctions.weActions(ForewardBtn, "clickElement","","Foreward Btn");
	    }
	 
	 public void  click_ResourcesTab() throws InterruptedException
	    {
	    	CommonFunctions.weActions(ResourcesTab, "clickElement","","Resources Tab");
	    }
	 public void  click_DownloadTab() throws InterruptedException
	    {
	    	CommonFunctions.weActions(DownloadTab, "clickElement","","Download Tab");
	    }
	 
	 public void  click_DocumentationTab() throws InterruptedException
	    {
	    	CommonFunctions.weActions(DocumentationTab, "clickElement","","Documentation Tab");
	    }
	
	
}
