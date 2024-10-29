package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testcontext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" + timeStamp + ".html";
		//sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		//sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkreporter = new ExtentSparkReporter(".\\reports\\" + "Test-Report-" + timeStamp + ".html");
		sparkreporter.config().setDocumentTitle("RestAssured Automation Project");
		sparkreporter.config().setReportName("Pet Store Users API");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		
		extent.setSystemInfo("Computer Name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name","Surya");
		extent.setSystemInfo("os","windows10");
		extent.setSystemInfo("browser Name","Chrome");
		
		String os= testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String broswer= testcontext.getCurrentXmlTest().getParameter("broswer");
		extent.setSystemInfo("Broswer", broswer);
		
		List<String> includedGroups =  testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
//		test =  extent.createTest(result.getName());
//		test.log(Status.PASS, "Test case Passed" + result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
//		test = extent.createTest(result.getName());
//		test.log(Status.FAIL,"Test case Failed" + result.getName());
//		test.log(Status.FAIL, "Test case failed cause" + result.getThrowable());
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
//		test = extent.createTest(result.getName());
//		test.log(Status.SKIP, "test case skipped" + result.getName());
	}
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
