package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportname;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportname="Test-Report - "+timeStamp+".html";  //Specifying the report name
		
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+reportname); //Specifying the location
		
		sparkReporter.config().setDocumentTitle("Pet Store Framework Development");
		sparkReporter.config().setReportName("Pet Store API's");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
	}
	
}
