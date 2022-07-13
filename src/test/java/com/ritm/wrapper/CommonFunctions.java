package com.ritm.wrapper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.Status;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.ritm.testcases.Base;

public class CommonFunctions extends Base {

	public CommonFunctions() {
		PageFactory.initElements(driver, this);
	}

	public enum weActions {
		isElementPresent, isElementVisible, isElementEnabled, actionClick, insertText, javaScriptinsertText, clickElement, clickTextElement, isTextPresent, clearText, getText, verifyInputText, lenghtOfElement, getBGColor, selectDropdown, isElementSelected, toUncheck, verifyElementText, takeScreenshot, linktext, Date_Operation, verifySelectedElementText, isElementDisabled, clickedButton, isElementNotSelected, swithctoframe, scrollview, javaScriptclickElement, ScrollIntoView, sendKeysText;
	}

	public enum expectconditions {
		swithctoframe, visibilityofElement, swithctoframeInside, visibilityofElementAfterRefresh, popUpClose;
	}

	public enum select {
		selectDropdownbyIndex, selectDropdownbyvalue, selectDropdown, selectDropdownaction;
	}

	public enum ElementCheck {
		isElementPresent, isElementVisible, isElementEnabled, isElementSelected, isElementDisabled, DisabledElementSelected, DisabledElementNotSelected, isElementNotSelected, isElementNotVisible, isHidden, isDisplayed, isChecked, isElementReadOnly;

	}

	public enum gettingText {
		getAttributeText, getText, getFirstsSlectedOptions, getDisabledValueText;

	}

	public enum getSize {
		getTableSize, getDropDownSize;

	}

	public static void expectconditions(WebElement returnedWE, String command) throws InterruptedException {
		try {
			switch (expectconditions.valueOf(command)) {
			case swithctoframe:
				driver.switchTo().defaultContent();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(returnedWE));
				break;
			case swithctoframeInside:
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(returnedWE));
				break;
			case visibilityofElement:
				wait.until(ExpectedConditions.visibilityOf(returnedWE));
				break;
			case visibilityofElementAfterRefresh:
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(returnedWE)));
				break;
			case popUpClose:
				driver.switchTo().defaultContent();
				returnedWE.click();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void weActions(WebElement returnedWE, String command, String data, String FieldName) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			switch (weActions.valueOf(command)) {
			case actionClick:
				Actions ob = new Actions(driver);
				ob.click(returnedWE).build().perform();
				break;
			case insertText:
				returnedWE.click();
				returnedWE.clear();
				returnedWE.sendKeys(data);
				returnedWE.sendKeys(Keys.TAB);
				child.log(Status.INFO, data + " is entered in " + FieldName);
				break;
			case javaScriptinsertText:
				jse.executeScript("arguments[0].scrollIntoView();", returnedWE);
				jse.executeScript("arguments[0].click();", returnedWE);
				returnedWE.clear();
				if (!(data.replaceAll(" ", "")).isEmpty()) {
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0," + returnedWE.getLocation().y + ")");

					((JavascriptExecutor) driver).executeScript("arguments[0].value=''", returnedWE);
					((JavascriptExecutor) driver).executeScript("arguments[0].value='" + data.trim() + "'", returnedWE);
				}
				returnedWE.sendKeys(Keys.TAB);
				child.log(Status.INFO, data + " is entered in " + FieldName);
				break;
			case clickElement:
				// wait.until(ExpectedConditions.elementToBeClickable(returnedWE));
				returnedWE.click();
				child.log(Status.INFO, FieldName + " is clicked ");
				break;
			case javaScriptclickElement:
				jse.executeScript("arguments[0].scrollIntoView();", returnedWE);
				jse.executeScript("arguments[0].click();", returnedWE);
				child.log(Status.INFO, FieldName + " is clicked ");
				break;
			case clearText:
				returnedWE.clear();
				child.log(Status.INFO, FieldName + " is cleared ");
				break;
			case ScrollIntoView:
				jse.executeScript("arguments[0].scrollIntoView();", returnedWE);
				child.log(Status.INFO, FieldName + " is Viewed");
				break;
			case sendKeysText:
				returnedWE.sendKeys(data);
				child.log(Status.INFO, data + " is entered in " + FieldName);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void select(WebElement returnedWE, String command, String data, int r, String FieldName) {
		try {
			Select dropdown = new Select(returnedWE);
			Actions action = new Actions(driver);
			switch (select.valueOf(command)) {
			case selectDropdownbyvalue:
				dropdown.selectByValue(data);
				break;
			case selectDropdownbyIndex:
				dropdown.selectByIndex(r);
				break;
			case selectDropdown:
				dropdown.selectByVisibleText(data);
				child.log(Status.INFO, data + " is chosen from " + FieldName + " field");
				break;
			case selectDropdownaction:
				dropdown.selectByVisibleText(data);
				action.moveToElement(returnedWE).click().build().perform();
				child.log(Status.INFO, data + " is chosen from " + FieldName + " field");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String gettingText(WebElement returnedWE, String command, String data, String FieldName) {
		String returnText = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;

			switch (gettingText.valueOf(command)) {
			case getText:
				returnText = returnedWE.getText();
				child.log(Status.INFO, FieldName + " value " + returnText + " is fetched");
				break;
			case getAttributeText:
				returnText = returnedWE.getAttribute(data);
				child.log(Status.INFO, FieldName + " value " + returnText + " is fetched");
				break;
			case getFirstsSlectedOptions:
				Select se = new Select(returnedWE);
				returnText = se.getFirstSelectedOption().getText();
				child.log(Status.INFO, FieldName + " value " + returnText + " is fetched");
				break;
			case getDisabledValueText:
				WebElement test = (WebElement) jse
						.executeScript("return document.getElementById('" + returnedWE + "')");
				returnText = test.getAttribute(data);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return returnText;
	}

	public static Boolean ElementCheck(WebElement returnedWE, String command, String data) {
		Boolean returnText = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			switch (ElementCheck.valueOf(command)) {
			case isElementVisible:
					returnText = returnedWE.isDisplayed();
				if (returnText) {
					child.log(Status.PASS, data + " is visible");
				} else {
					child.log(Status.FAIL, data + " is not visible");
				}
				break;
			case isElementEnabled:
				returnText = returnedWE.isEnabled();
				if (returnText) {
					child.log(Status.PASS, data + " is Enabled");
				} else {
					child.log(Status.FAIL, data + " is not Enabled");
				}
				break;
			case isElementSelected:
				returnText = returnedWE.isSelected();
				if (returnText) {
					child.log(Status.PASS, data + " is Selected");
				} else {
					child.log(Status.FAIL, data + " is not Selected");
				}
				break;
			case isChecked: // Not for validation Purpose
				returnText = returnedWE.isSelected();
				if (returnText) {
					child.log(Status.INFO, data + " is Selected");
				} else {
					child.log(Status.INFO, data + " is not Selected");
				}
				break;
			case isElementDisabled:
				wait.until(ExpectedConditions.visibilityOf(returnedWE));
				returnText = !(returnedWE.isEnabled());
				if (returnText) {
					child.log(Status.PASS, data + " field is Disabled");
				} else {
					child.log(Status.FAIL, data + " field is not Disabled");
				}
				break;
			case DisabledElementSelected:
				WebElement element = (WebElement) jse
						.executeScript("return document.getElementById('" + returnedWE + "')");
				returnText = element.isSelected();
				if (returnText) {
					child.log(Status.PASS, data + " is Selected");
				} else {
					child.log(Status.FAIL, data + " is not Selected");
				}
				break;
			case DisabledElementNotSelected:
				WebElement Element = (WebElement) jse
						.executeScript("return document.getElementById('" + returnedWE + "')");
				returnText = Element.isSelected();
				if (!returnText) {
					child.log(Status.PASS, data + " is not Selected");
				} else {
					child.log(Status.FAIL, data + " is Selected");
				}
				break;
			case isElementNotSelected:
				returnText = !(returnedWE.isSelected());
				if (returnText) {
					child.log(Status.PASS, data + " is not Selected");
				} else {
					child.log(Status.FAIL, data + " is Selected");
				}
				break;
			case isElementNotVisible:
				returnText = !returnedWE.isDisplayed();
				if (returnText) {
					child.log(Status.PASS, data + " is not Visible");
				} else {
					child.log(Status.FAIL, data + " is Visible");
				}
				break;
			case isHidden: {
				String style = returnedWE.getAttribute("style");
				returnText = (style.contains("display: none") || style.contains("hidden")
						|| (returnedWE.getAttribute("hidden") != null));
				if (returnText)
					child.log(Status.PASS, data + " is hidden");
				else
					child.log(Status.FAIL, data + " is not hidden");
			}
				break;
			case isDisplayed: {
				String style = returnedWE.getAttribute("style");
				returnText = (style.contains("display: none") || style.contains("hidden")
						|| (returnedWE.getAttribute("hidden") != null));
				returnText = !returnText;
				if (returnText)
					child.log(Status.PASS, data + " field is displayed");
				else
					child.log(Status.FAIL, data + " field is not displayed");
			}
				break;
			case isElementReadOnly:
				returnText = returnedWE.getAttribute("readOnly").equals("true");
				if (returnText) {
					child.log(Status.PASS, data + " is ReadOnly");
				} else {
					child.log(Status.FAIL, data + " is not ReadOnly");
				}
				break;
			default:
				break;
			}

		} catch (Exception e) {
			if(e instanceof NoSuchElementException)
				child.log(Status.FAIL, data + ": WebElement not found");
			System.out.println(e);

		}
		return returnText;
	}

	public static int getSize(List<WebElement> table, String command, String FieldName) {
		int returncount = 0;
		switch (getSize.valueOf(command)) {
		case getTableSize:
			returncount = table.size();
			child.log(Status.INFO, FieldName + " value " + returncount + " table size");
			break;
		default:
			break;
		}
		return returncount;
	}

	public static int getdropdownSize(WebElement returnedWE, String command, String FieldName) {
		int returncount = 0;
		Select dropdown = new Select(returnedWE);
		switch (getSize.valueOf(command)) {
		case getDropDownSize:
			List<WebElement> dropdownvalue = dropdown.getOptions();
			returncount = dropdownvalue.size();
			child.log(Status.INFO, FieldName + " value " + returncount + " dropdown size");
			break;
		default:
			break;
		}
		return returncount;

	}

	public List<String> fetchValues(List<WebElement> list) {
		List<String> values = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (WebElement element : list) {
			jse.executeScript("arguments[0].scrollIntoView();", element);
			values.add(element.getText().replaceAll("  ", " ").trim());
		}
		System.out.println(values);
		return values;
	}

	public List<String> fetchListofAttributeValues(List<WebElement> list, String data) {
		List<String> values = new ArrayList<>();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		for (WebElement element : list) {
			jse.executeScript("arguments[0].scrollIntoView();", element);
			values.add(element.getAttribute(data).replaceAll("  ", " ").trim()); // getText
																					// getAttribute(data)
		}
		System.out.println(values);
		return values;
	}

	public void dropDownValidation(String value, List<String> options, String field) {
		String[] values = value.split(";");
		int flag = 1;
		if (values.length == options.size()) {
			for (int i = 0; i < values.length; i++) {

				if (!options.contains(values[i])) {
					flag = 0;
					break;
				}
			}
			if (flag == 0)
				child.log(Status.FAIL,
						"Both expected and actual " + field + " dropdown values are not matched. <br><b>Expected:</b>"
								+ value + " <br><b>Actual:</b>" + options);
			else
				child.log(Status.PASS, "Both expected and actual " + field + " dropdown values are matched.");
		} else {
			child.log(Status.FAIL,
					"Both expected and actual " + field + " dropdown values are not matched. <br><b>Expected:</b>"
							+ value + " <br><b>Actual:</b>" + options);
		}

	}

	public static void uploadDocument(String Documentpath) throws AWTException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(Documentpath);
		clipboard.setContents(stringSelection, null);
		Robot ob = new Robot();
		ob.keyPress(KeyEvent.VK_CONTROL);
		ob.keyPress(KeyEvent.VK_V);
		ob.keyRelease(KeyEvent.VK_V);
		ob.keyRelease(KeyEvent.VK_CONTROL);
		try {
			ob.keyPress(KeyEvent.VK_TAB);
			System.out.println("First Tab");
			ob.keyPress(KeyEvent.VK_TAB);
			System.out.println("Second Tab");
			ob.keyPress(KeyEvent.VK_TAB);
			System.out.println("Third Tab");
			ob.keyPress(KeyEvent.VK_ENTER);
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("Nothing");
		}
	}

	public static void uploadDocuauto() throws IOException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\TestData\\UploadDocument.exe");
	}

	public static boolean numericCompare(double value1, double value2, String field) {
		boolean flag = false;
		try {
			if (value1 == value2) {
				child.log(Status.PASS, "Both expected and actual " + field + " are equal. \nExpected: " + value1
						+ " \nActual: " + value2);
				flag = true;
			} else {
				child.log(Status.FAIL, "Both expected and actual " + field + " are not equal. \nExpected: " + value1
						+ " \nActual: " + value2);
			}
		} catch (Exception e) {
			child.log(Status.FAIL, "Both expected and actual " + field + " are not equal. \nExpected: " + value1
					+ " \nActual: " + value2);
		}
		return flag;
	}

	public static boolean stringCompare(String value1, String value2, String field) {
		boolean flag = false;
		try {
			if (value1.equalsIgnoreCase(value2)) {
				child.log(Status.PASS, "Both expected and actual " + field + " are equal");
				flag = true;
			} else {
				child.log(Status.FAIL, "Both expected and actual " + field + " are not equal. Expected: " + value1
						+ " Actual: " + value2);
			}
		} catch (Exception e) {
			child.log(Status.FAIL,
					"Both expected and actual " + field + " are not equal. Expected: " + value1 + " Actual: " + value2);
		}
		return flag;
	}

	public static boolean stringCompareContains(String value1, String value2, String field) {
		boolean flag = false;
		try {
			if (value1.contains(value2)) {
				child.log(Status.PASS, "Both expected and actual " + field + " are equal");
				flag = true;
			} else {
				child.log(Status.FAIL, "Both expected and actual " + field + " are not equal. Expected: " + value1
						+ " Actual: " + value2);
			}
		} catch (Exception e) {
			child.log(Status.FAIL,
					"Both expected and actual " + field + " are not equal. Expected: " + value1 + " Actual: " + value2);
		}
		return flag;
	}

	public void ListValidation(String value, List<String> options, String field) {
		String[] values = value.split(";");
		int flag = 1;
		if (values.length == options.size()) {
			for (int i = 0; i < values.length; i++) {
				// trim added for atAglance validation
				if (!options.contains(values[i].trim())) {
					flag = 0;
					break;
				}
			}
			if (flag == 0)
				child.log(Status.FAIL,
						"Both expected and actual " + field + " list values are not matched. <br><b>Expected:</b>"
								+ value + " <br><b>Actual:</b>" + options);
			else
				child.log(Status.PASS, "Both expected and actual " + field + " list values are matched.");
		} else {
			child.log(Status.FAIL, "Both expected and actual " + field
					+ " list values are not matched. <br><b>Expected:</b>" + value + " <br><b>Actual:</b>" + options);
		}
	}

	public void ListToListValidation(List<String> values1, List<String> values2, String Data) {
		int flag = 1;
		if (values1.equals(values2)) {
			for (int i = 0; i < values1.size(); i++) {
				if (!values1.get(i).contains(values2.get(i))) {
					flag = 0;
					break;
				}
			}
			if (flag == 0)
				child.log(Status.FAIL,
						"Both expected and actual " + Data + " list values are not matched. <br><b>Expected:</b>"
								+ values1 + " <br><b>Actual:</b>" + values2);
			else
				child.log(Status.PASS, "Both expected and actual " + Data + " list values are matched.");
		} else {
			child.log(Status.FAIL, "Both expected and actual " + Data
					+ " list values are not matched. <br><b>Expected:</b>" + values1 + " <br><b>Actual:</b>" + values2);
		}
	}

	public String subString(String value, int size) {
		value = value.trim().length() > size ? value.trim().substring(0, size) : value.trim();
		return value;
	}

	public static double stringToDouble(String value) {
		if (value.equals(""))
			return 0;
		else
			return value.contains("(") ? -Double.parseDouble(value.replaceAll("[^0-9-.]", "").trim())
					: Double.parseDouble(value.replaceAll("[^0-9-.]", ""));
	}

	/************************** Employee Journey *************************/

	public static ArrayList<String> getAllOptions(WebElement returnedWE) {
		ArrayList<String> WorkItems = new ArrayList<String>();
		Select se = new Select(returnedWE);
		List<WebElement> allOptions = se.getOptions();
		// Loop to print one by one
		for (int j = 0; j < allOptions.size(); j++) {
			WorkItems.add(allOptions.get(j).getText());
		}
		return WorkItems;
	}

	public static void File_Formatter(File SourcePath, String FindChar, String ReplaceChar) throws IOException {
		FileReader fr = new FileReader(SourcePath);
		String s;
		String totalStr = "";
		try {
			BufferedReader br = new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				totalStr += s;
			}
			totalStr = totalStr.replaceAll(FindChar, ReplaceChar);
			FileWriter fw = new FileWriter(SourcePath);
			fw.write(totalStr);
			fw.close();
			br.close();
		} catch (Exception e) {
			System.out.println("Characters not Changed");
		}
	}

	public static void OpenNewWindow(String web) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String jsOpenNewWindow = "window.open('" + web + "');";
		jsExecutor.executeScript(jsOpenNewWindow);
	}

	public static void switchtoWindowUsingPageURL(String URL, String parentWindow, ArrayList<String> NumberOfTabs) {
		driver.switchTo().defaultContent();
		for (String windowHandle : NumberOfTabs) {
			if (driver.switchTo().window(windowHandle).getCurrentUrl().contains(URL)) {
				driver.manage().window().maximize();
				break;
			} else {
				driver.switchTo().window(parentWindow);
			}
		}
	}

	public static String getParentWindow() {
		return driver.getWindowHandle();
	}

	public static boolean VerifyData_Array(String Value, String ArrayValues[]) {
		int size = ArrayValues.length;
		boolean result = false;
		for (int i = 0; i < size; i++) {
			if (Value.equals(ArrayValues[i])) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static String readLineFromFile(FileReader fr, int lineNumber) throws IOException {
		try (BufferedReader br = new BufferedReader(fr)) {
			for (int i = 1; i < lineNumber; i++)
				br.readLine();
			return br.readLine();
		}
	}

	public static Recordset ReadDataFromDataSheet(String strModuleSheetName, int rowNum) {
		Recordset objRS = null;
		try {
			Fillo objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\Excel\\PDF_Validation.xlsx");
			String strQuery = "select * from " + strModuleSheetName + " where Row_Num='" + rowNum + "'";
			System.out.println(strQuery);
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			return objRS;
		} catch (Exception IOE) {
			System.out.println("Error Occurred is: " + IOE.getMessage());
		}
		return objRS;
	}

	// Path Added by Jawahar 05/21/19
	public static int getRowCount(String Path, String sheetName)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		FileInputStream fis = new FileInputStream(Path);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}

	public static void ConvertPDFToText() throws InvalidPasswordException, IOException {
		PDDocument doc = PDDocument.load(new File(System.getProperty("user.dir") + "\\TestData\\flood_cert.pdf"));
		String text = new PDFTextStripper().getText(doc);
		;
		System.out.println("Text in PDF\n---------------------------------");
		try (FileWriter writer = new FileWriter(new File(System.getProperty("user.dir") + "\\Excel\\Flood_Text.txt"));
				BufferedWriter bw = new BufferedWriter(writer)) {
			bw.write(text);
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		doc.close();
	}

	// Jawahar Excel File Added in argument 05/21/19
	public static void setExcelData(String Path, String sheetName, int rowNum, int colNum, String data)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook wb = WorkbookFactory.create(new FileInputStream(Path));
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		if (row == null) {
			row = sh.createRow(rowNum);
		}
		// End
		Cell cell = row.createCell(colNum);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(Path);
		wb.write(fos);
	}

	public static void setExcelFormula(String Path, String sheetName, int rowNum, int colNum, String data)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		Workbook wb = WorkbookFactory.create(new FileInputStream(Path));
		Sheet sh = wb.getSheet(sheetName);
		System.out.println("row is : " + rowNum);
		Row row = sh.getRow(rowNum);
		if (row == null) {
			row = sh.createRow(rowNum);
		}
		// End
		Cell cell = row.createCell(colNum);
		System.out.println("cell cretated :" + cell);
		cell.setCellType(CellType.STRING);
		cell.setCellFormula(data);
		FileOutputStream fos = new FileOutputStream(Path);
		wb.write(fos);
	}

}
