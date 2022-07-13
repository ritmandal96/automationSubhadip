package com.ritm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.testng.annotations.Parameters;

import com.ritm.wrapper.CommonFunctions;
@Parameters({"Protocol","Environment"})
public class Login_Page extends CommonFunctions   {
	
	/********Constructor*********/
	
	public Login_Page(WebDriver driver) {
		super();
    }
	
	//NOX Coverage
    /***GFXLogon.htm,http://demo.lendingcloud.net/NetOxygen/common/html_modals/modal_select_group.htm ****/
	
	/**
     * All WebElements are identified by @FindBy annotation
     */  
    @FindBy(how=How.XPATH,using="//a[@href='https://network.zmanda.com/']")
     WebElement LoginLink;

    @FindBy(how=How.ID,using="emailAddress")
    WebElement Email;
    
    @FindBy(how=How.ID,using="password")
    WebElement password;

    @FindBy(how=How.LINK_TEXT,using="LOGIN")
    WebElement loginbutton;

    /*@FindBy(how=How.ID,using="selectGroup")
    WebElement securityRole;
  
    @FindBy(how=How.ID,using="btnOk")
    WebElement roleselection;
    
    @FindBy(how=How.ID,using="GFXLogonFrame")
    WebElement loginframe;
   
    @FindBy(how=How.ID,using="grpselect")
    WebElement roleframe;
    
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Select Security Group') or contains(text(),'Select Group')]")
	WebElement SecurityGroupText;
    
    @FindBy(how = How.XPATH, using = "//iframe[contains(@src,'modal_select_group.htm')]")
	WebElement selectGroupFrame;*/

   
    /********** Page Method are written below *******************/
    
    /*********Entering UserName *****************/  
    
    public void  click_Login() throws InterruptedException
    {
    	CommonFunctions.weActions(LoginLink, "clickElement","","Home Page");
    }
    
    public void  insertText_username(String uname) throws InterruptedException
    {
    	CommonFunctions.weActions(Email, "insertText",uname,"Username Textbox");
    }
    
    public void  insertText_password(String pwd) throws InterruptedException
    {
    	CommonFunctions.weActions(password, "insertText",pwd,"Password Textbox");
    }
    
   /* public void  swithctoframe_loginframe() throws InterruptedException
    {
    	CommonFunctions.expectconditions(loginframe, "swithctoframe");
    }
    public void swithctoframe_selectGroupFrame() throws InterruptedException {
   
		CommonFunctions.expectconditions(selectGroupFrame, "swithctoframeInside");
	}
    
    

    public boolean isVisible_SecurityGroupText() {
    	
		return SecurityGroupText.isDisplayed();
	}


    public void  insertText_username(String strusername) throws InterruptedException
    {
    	CommonFunctions.weActions(username, "insertText",strusername,"Username Textbox");
    	
    }
    
    public void  insertText_PassWord(String strpassword)
    {	
    	CommonFunctions.weActions(password, "insertText",strpassword,"Password");
        
    }
   
    public void clickElement_loginbutton()
    {		
    	CommonFunctions.weActions(loginbutton, "clickElement", "","Log On Button");
        
    }
  
   
    
     
    public void selectDropdown_securityRole(String secuityrole)
    {
    	CommonFunctions.select(securityRole, "selectDropdown",secuityrole,0," Security Role");
    }
    //select_Security
    public void clickElement_roleselection()
    {
    		CommonFunctions.weActions(roleselection, "clickElement","","Ok button");

    }*/
   
   
}
