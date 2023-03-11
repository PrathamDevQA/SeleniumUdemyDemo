package testNgDemo.AbstarctPackage.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTeportersNG {

	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter repoters = new ExtentSparkReporter(path);
		repoters.config().setDocumentTitle("Test Result");
		repoters.config().setReportName("Web Automation Testing Demo");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(repoters);
		extent.setSystemInfo("Tester", "Pratham Patel");
		return extent;
		
	}
}
