package MichaelSeleniumAutomation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsTestNG {
	
	
	public static ExtentReports getReportObject() {

		// ExtentReports , ExtentSparkReporter
		String path = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reportDocument = new ExtentSparkReporter(path);

		reportDocument.config().setReportName("Web Automation Results");
		reportDocument.config().setDocumentTitle("Test Results");

		ExtentReports	extent = new ExtentReports();
		extent.attachReporter(reportDocument);
		extent.setSystemInfo("Automation Engineer", "Michael Djamba");
		
		return extent;

	}

}
