package com.ritm.wrapper;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import com.aventstack.extentreports.Status;
import com.ritm.testcases.Base;

public class FileUtilities extends Base {

	public File[] ReturnQueriedFile(String Filepath, String LNUM, String FileFilter, String EndFilter)
			throws InterruptedException, IOException {

		File source = new File(Filepath);
		FileFilter fileFilter = new WildcardFileFilter(FileFilter + "*" + LNUM + "*" + EndFilter);
		File[] files = source.listFiles(fileFilter);
		Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		return files;

	}

	public String ReturnLatestFile(String Server1, String Server2, String LNUM, String FileFilter, String EndFilter) {
		String Payload1 = "0";
		String Payload = null;
		String Payload2 = "0";
		String XML1 = null;
		String XML2 = null;
		int Files1 = 0;
		int Files2 = 0;
		try {
			// "Flood_FNCCreateOrderReq_" + LNum + "*_ExportTransformed.xml"
			File dir = new File(Server1);
			FileFilter fileFilter = new WildcardFileFilter(FileFilter + "*" + LNUM + "*" + EndFilter);
			File[] files = dir.listFiles(fileFilter);
			Files1 = files.length;
			for (int i = 0; i < Files1; i++) {
				Payload1 = String.valueOf(files[i]);
			}
			XML1 = Payload1;
			Payload1 = Payload1.replace(Server1+"\\", "");
			Payload1 = Payload1.replace(FileFilter + LNUM + "_", "");
			Payload1 = Payload1.replace(EndFilter, "");
			Payload1 = Payload1.replaceAll("_", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			File dir1 = new File(Server2);
			FileFilter fileFilter1 = new WildcardFileFilter(FileFilter + "*" + LNUM + "*" + EndFilter);
			File[] files1 = dir1.listFiles(fileFilter1);
			Files2 = files1.length;
			for (int i = 0; i < Files2; i++) {
				Payload2 = String.valueOf(files1[i]);
			}
			XML2 = Payload2;
			Payload2 = Payload2.replace(Server2 + "\\", "");
			Payload2 = Payload2.replace(FileFilter + LNUM + "_", "");
			Payload2 = Payload2.replace(EndFilter, "");
			Payload2 = Payload2.replaceAll("_", "");
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(Payload1);
		System.out.println(Payload2);
		BigInteger val1 = new BigInteger(Payload1);
		BigInteger val2 = new BigInteger(Payload2);

		int high = val1.compareTo(val2);
		if (high == 1) {
			Payload = XML1;
		} else if (high == 0) {
			child.log(Status.INFO, "No files were found in Both Server");
			Base.setAbortTest(true);
		} else {
			Payload = XML2;
		}
		return Payload;
	}

	public void copyFile(File Source, String dest) throws IOException {
		File Dest = new File(dest);
		Files.copy(Source.toPath(), Dest.toPath());
	}

	public void copyFileFromServer(String Payload, String dest) throws IOException {
		try {
			File Source = new File(Payload);
			File Dest = new File(dest);
			FileUtils.copyFile(Source, Dest);
			//Files.copy(Source.toPath(), Dest.toPath());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
