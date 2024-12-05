package MichaelSeleniumAutomation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		int count = 0;
		int MaxRetry =1;
		
		if(count<MaxRetry) {
			count++;
			return true;
		}
		return false;
	}

}
