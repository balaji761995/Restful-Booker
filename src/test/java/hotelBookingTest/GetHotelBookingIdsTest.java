package hotelBookingTest;

import DTOs.GetBookingIdsResponseDTO;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.BaseTest;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;

public class GetHotelBookingIdsTest extends BaseTest {

    String basePath = "/booking";

    @Test
    public void getHotelBookingIdsTest(){

        RequestSpecification getBookingIdsRequest = RestAssured.given();
        getBookingIdsRequest.headers(headers);
        getBookingIdsRequest.basePath(basePath);

        Response response = getBookingIdsRequest.filter(new RequestLoggingFilter(requestCapture)).when().get();

        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);

        List<GetBookingIdsResponseDTO> bookingIdsResponse = response.body().jsonPath().getList("",GetBookingIdsResponseDTO.class);

        requestCapture.flush();

        //logging request and response in extent report
        extentTest.get().log(LogStatus.INFO, requestWriter.toString());
        extentTest.get().log(LogStatus.INFO, "Response : " + response.asPrettyString());

        softAssert.assertNotNull(bookingIdsResponse);

        softAssert.assertAll();
    }
}
