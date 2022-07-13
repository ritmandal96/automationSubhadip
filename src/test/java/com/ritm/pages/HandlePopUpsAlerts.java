package com.ritm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ritm.wrapper.CommonFunctions;

/**********
 * NOX coveragae
 * 
 * @author AP20000215
 * @since 2018-10-30
 * @version 1.0
 *************/

public class HandlePopUpsAlerts extends CommonFunctions {
	
	public HandlePopUpsAlerts(WebDriver driver) {
		super();
	}

	@FindBy(how = How.ID, using = "results")
	WebElement PopUpMessage;

	@FindBy(how = How.ID, using = "message")
	WebElement PromptMessage;

	@FindBy(how = How.ID, using = "Yes")
	WebElement Yes;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id,'USBClientOkCancelPrompt')]")
	WebElement OkCancelFrame;

	@FindBy(how = How.ID, using = "No")
	WebElement No;

	@FindBy(how = How.TAG_NAME, using = "button")
	WebElement Close;

	@FindBy(how = How.ID, using = "YesAll")
	WebElement YesAll;

	@FindBy(how = How.ID, using = "Cancel")
	WebElement PopUpCancelButton;

	@FindBy(how = How.XPATH, using = "//input[@value='OK']")
	WebElement PopUpOkButton;
	
	@FindBy(how = How.ID, using = "OK")
	WebElement PromptOkButton;

	@FindBy(how = How.XPATH, using = "//input[@value='OK']")
	WebElement DupMessageOkButton;
	
	public static void handlePopUp() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(
				By.xpath("//iframe[@src='" + Protocol + "://" + Environment + "/common/html/GFXClientMessage.htm']")));
	}

	public static void handleAlertYesNo() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(
				"//iframe[@src='" + Protocol + "://" + Environment + "/common/html/GFXClientYesNoPrompt.htm']")));
	}

	public static void handleAlertOkCancel() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://"+ Environment + "/common/html/USBClientOkCancelPrompt.htm']")));
	}//*[contains(@id,'USBClientOkCancelPrompt')]
	
	public static void handleAlertYesNoCancel() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(
				"//iframe[@src='" + Protocol + "://" + Environment + "/common/html/GFXClientYesNoprompt.htm']")));
	}

	public static void DupLoanMessage() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(
				By.xpath("//iframe[@src='" + Protocol + "://" + Environment + "/common/html/GFXDupLoanMessage.htm']")));
	}
	
	

	public static void handleModalCredit() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_othercredits_parent.htm']")));
	}

	public static void handleQueryToken() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_CMS_query_tokens.htm']")));
	}

	public static void ProductPopUps() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(
				By.xpath("//iframe[@src='" + Protocol + "://" + Environment + "/common/html/GFPSClientMessage.htm']")));
	}

	public static void ImportExportpopup() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/NetOxygen/common/transferengine/GFXImportExport.htm']")));
	}

	public static void switchToSelectGroupFrame() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(
				"//iframe[@src='" + Protocol + "://" + Environment + "/common/html_modals/modal_select_group.htm']")));
	}

	public static void handleQueryFilterToken() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(
				"//iframe[@src='" + Protocol + "://" + Environment + "/common/html_modals/GFXLSQueryTokens.htm']")));

	}

	public static void handleUSBClientYesNoPrompt() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(
				"//iframe[@src='" + Protocol + "://" + Environment + "/common/html/USBClientYesNoPrompt.htm']")));

	}

	public static void handlevestingFrame() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_vesting_button.htm']")));

	}

	public static void OtherCreditsFrame() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_othercredits_parent.htm']")));

	}

	public static void LegalDescriptionPopUp() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_subj_prop_legal_desc.htm']")));
	}
	public static void ProductPopUps(String Protocol,String Environment)
    {
           driver.switchTo().defaultContent();
           driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://"
                        + Environment + "/common/html/GFPSClientMessage.htm']")));
    }
	public String getText_PopUpMessage() {
		handlePopUp();
		return CommonFunctions.gettingText(PopUpMessage, "getText", "", "Fetching PopUpMessage");
	}
	public String getText_PopUpMessageText() {
		handlePopUp();
		return CommonFunctions.gettingText(PopUpMessage, "getText", "", "Fetching PopUpMessage");

	}
	/******Same popup handle*****/
	public void clickElement_PopUpOkButton() {
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
	}

	public void clickElement_PopUpOKbutton() {
		handlePopUp();
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
	}
	/**********Completed**********/
	public String Get_AlertYesNoPrompt() {
		handleAlertYesNo();
		return CommonFunctions.gettingText(PromptMessage, "getText", "", "Fetching PopUpMessage");
	}
	
	public String Get_AlertOkCancelPrompt() {
		handleAlertOkCancel();
		return CommonFunctions.gettingText(PromptMessage, "getText", "", "Fetching PopUpMessage");
	}
	//Added for Appraisal - By Subhadip
	public void clickElement_AlertOkCancelPrompt() {
		handleAlertOkCancel();
		CommonFunctions.weActions(PromptOkButton, "clickElement", "", "Click OK");
	}

	//*[contains(@id,'USBClientOkCancelPrompt')]
	
	public String Get_AlertYesNoCancelPrompt() {
		handleAlertYesNoCancel();
		return CommonFunctions.gettingText(PromptMessage, "getText", "", "Fetching PopUpMessage");
	}

	public void clickElement_AlertYesNoPrompt() {
		CommonFunctions.weActions(Yes, "clickElement", "", "Click Yes");
	}

	public void clickElement_AlertNoPrompt() {
		CommonFunctions.weActions(No, "clickElement", "", "Click Yes");
	}

	public void clickElement_Doboth() {
		CommonFunctions.weActions(YesAll, "clickElement", "", "Click Yes");
	}

	public void Click_DupLoanMessage() {
		DupLoanMessage();
		CommonFunctions.weActions(DupMessageOkButton, "clickElement", "", "DupLoanMessage prompt OK Button");
	}

	public void handlemodal() {
		handleModalCredit();
	}

/*	public void HandleAlertMessage() {
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();
	}
*/
	public void handleQueryTokens() {
		handleQueryToken();
	}

	public String GetClose_PopUpMessages() {
		handlePopUp();
		String Message = CommonFunctions.gettingText(PopUpMessage, "getText", "", "Fetching PopUpMessage");
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
		return Message;
	}

	public String Get_PopUpMessages() {
		handlePopUp();
		String Message = CommonFunctions.gettingText(PopUpMessage, "getText", "", "Fetching PopUpMessage");
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
		return Message;
	}

	public void Click_ProductOkBtn() {
		ProductPopUps();
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
	}

	public void handleImportExportpopup() {
		ImportExportpopup();
	}

	public void handleQueryFilterTokens() {
		handleQueryFilterToken();
		CommonFunctions.weActions(PopUpCancelButton, "clickElement", "", "Cancel Button");
	}

	public void handleUSBclientYesNo() {
		handleUSBClientYesNoPrompt();
		CommonFunctions.weActions(Yes, "clickElement", "", "Yes Button");
	}

	public void handleUSBclientYesNo_temp() {
		handleUSBClientYesNoPrompt();
		CommonFunctions.weActions(No, "clickElement", "", "No Button");
	}

	public void switchToLegalDescPopup() {
		LegalDescriptionPopUp();
	}

	public void handleVestingFrame() {
		handlevestingFrame();

	}

	public void OtherCreditsPage() {
		OtherCreditsFrame();

	}

	public void switchToGroupFrame() {
		switchToSelectGroupFrame();
	}

	public void handleModallegalDesc1() {
		handleModallegalDesc();
	}

	public void Click_ALertYes() {
		handleAlertYesNo();
		CommonFunctions.weActions(Yes, "clickElement", "", "Click Yes");
	}

	public void expectconditions_close() throws InterruptedException {
		CommonFunctions.expectconditions(Close, "popUpClose");
	}
	
	public static void handleModallegalDesc() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://" + Environment
				+ "/common/html_modals/modal_subj_prop_legal_desc.htm']")));
	}

	public void PopUpOKVerify() {
		handlePopUp();
		CommonFunctions.weActions(PopUpOkButton, "javaScriptclickElement", "", "OK Button");
	}

	public void getClose_PopUpMessage() {
		CommonFunctions.weActions(PopUpOkButton, "clickElement", "", "OK Button");
	}
	 public void ProductDetailsPopup()
	    {   
		 ProductPopUps(); 
	    }
	    public void clickElement_PopUpOkButtonProductPopup()
	    { 
	           ProductPopUps(); 
	           CommonFunctions.weActions(PopUpOkButton, "clickElement","","OK Button"); 
	    }
	public void clickMessageBoxIfAny() throws InterruptedException
    { 
           HandlePopUpsAlerts popup=new HandlePopUpsAlerts(driver);
           driver.switchTo().defaultContent();
           Thread.sleep(500);
           while(driver.getPageSource().contains("GFXClientMessage.htm"))
           {
           try
           {
           popup.clickElement_PopUpOKbutton();
           driver.switchTo().defaultContent();
           Thread.sleep(500);
           }
           catch(Exception e)
           {
                  System.out.println("No Popups");
           }
           }
    }

	
	public void clickSwichAnyWay() throws InterruptedException
    {
           driver.switchTo().defaultContent();
           String frame="src=\"" + Protocol + "://"+ Environment +"/common/html/incompletefields.htm\"";
           if(driver.getPageSource().contains(frame))
           {
           try {
                  
                  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='" + Protocol + "://"
                        + Environment +"/common/html/incompletefields.htm']")));
                  Actions actions = new Actions(driver);

                  actions.moveToElement(driver.findElement(By.id("IncompFields_OK"))).click().perform();
           } catch (Exception e) {
                  System.out.println(e);
           }
           }
           clickMessageBoxIfAny();
    }
	public void HandleAlertMessage() {
        try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);

        } catch (AWTException e) {
                        e.printStackTrace();
        } 
	}
	public void clickAlert() {
        try {
               wait.until(ExpectedConditions.alertIsPresent());
               Alert alert = driver.switchTo().alert();
               alert.accept();
        } catch (Exception e) {
               System.out.println(e);
        }
 }
	
	public static void IncompleteFieldsFrame() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(
				By.xpath("//iframe[@src='" + Protocol + "://" + Environment + "/common/html/incompletefields.htm']")));

	}
	
	@FindBy(how = How.ID, using = "IncompFields_OK")
	WebElement SwitchTasks;
	
	public void handleIncompleteFieldsPopup() {
		IncompleteFieldsFrame();
		CommonFunctions.weActions(SwitchTasks, "javaScriptclickElement", "", " Switch Tasks Button");
	}
}
