package TestNG_Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListerner implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("Test Cases are Started and details are: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("Test Cases are Passed and details are: " + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Test Cases are Failed and details are: " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Cases are Skipped and details are: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
