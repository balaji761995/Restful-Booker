package steps;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener  {

    @Override
    public void onTestStart(ITestResult result) {
        if(result.getParameters().length==0){
            test = extentReports.startTest(result.getMethod().getMethodName());
        }else {
            test = extentReports.startTest(result.getMethod().getMethodName()+(result.getParameters()[0].toString()));
        }
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(LogStatus.PASS,"Test Passed");
        extentReports.endTest(extentTest.get());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.get().log(LogStatus.FAIL, result.getThrowable());
            extentReports.endTest(extentTest.get());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        extentReports.close();
    }
}
