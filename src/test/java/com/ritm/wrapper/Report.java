package com.ritm.wrapper;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Report {
	
	public static String captureScreenShot(WebDriver driver,String screenshotname) {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Path dest = Paths.get(System.getProperty("user.dir")+"\\screenshots", screenshotname+"_"+dateName+ ".png");
		//Path dest = Paths.get("./Reports/screenshots/", screenshotname+"_"+dateName+ ".png"); //New Path Config
		
		String screenshot = null;
		
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			screenshot ="."+dest.toString();   // dot added with link
			
		} catch (Exception e) {

			System.out.println("Exception while taking screenshot"+e);
		}
		return screenshot;	
}
}
