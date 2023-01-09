package steps;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static steps.BaseTest.extentTest;

public class SendRequest {

    public static Response sendRequest(RequestSpecification request, String requestType) {

        StringWriter requestWriter = new StringWriter();
        PrintStream requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
        Response response = null;

        if (requestType.equalsIgnoreCase("post")) {
            response = RestAssured.given().spec(request).filter(new RequestLoggingFilter(requestCapture)).when().post();
        } else if (requestType.equalsIgnoreCase("get")) {
            response = RestAssured.given().spec(request).filter(new RequestLoggingFilter(requestCapture)).when().get();
        } else if (requestType.equalsIgnoreCase("put")) {
            response = RestAssured.given().spec(request).filter(new RequestLoggingFilter(requestCapture)).when().put();
        } else if (requestType.equalsIgnoreCase("patch")) {
            response = RestAssured.given().spec(request).filter(new RequestLoggingFilter(requestCapture)).when().patch();
        } else if (requestType.equalsIgnoreCase("delete")) {
            response = RestAssured.given().spec(request).filter(new RequestLoggingFilter(requestCapture)).when().delete();
        }

        requestCapture.flush();

        try {
            extentTest.get().log(LogStatus.INFO, requestWriter.toString());
            extentTest.get().log(LogStatus.INFO, "Response : " + response.asPrettyString());
        } catch (Exception e) {
            //Do Nothing
        }

        return response;
    }


}
