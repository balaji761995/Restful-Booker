package hotelBookingTest;

import DTOFactories.BookingPayloadDTOFactory;
import DTOs.BookingPayloadDTO;
import DTOs.CreateBookingResponseDTO;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.BaseTest;
import steps.DataProviders;
import steps.GetBookingDetails;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;

public class DeleteHotelBookingTest extends BaseTest {

    String basePathCreateBooking = "/booking";
    String basePathDeleteBooking = "/booking/{id}";


    @Test(dataProvider = "createHotelBookingTestData",dataProviderClass = DataProviders.class)
    public void deleteHotelBookingTest(ArrayList<String> testData){

        BookingPayloadDTO payload = new BookingPayloadDTOFactory().createBookingPayloadDTOFactoryWithDataProviderTestData(testData);

        //Creating Request specifications
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.addHeader("Content-Type", "application/json");
        requestSpec.setBody(payload);
        requestSpec.setBasePath(basePathCreateBooking);

        RequestSpecification createRequest = requestSpec.build();

        //Post Request to create hotel booking
        Response response = RestAssured.given().spec(createRequest).filter(new RequestLoggingFilter(requestCapture))
                .when().post();

        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);

        CreateBookingResponseDTO createBookingResponse = response.as(CreateBookingResponseDTO.class);

        requestCapture.flush();

        //logging request and response in extent report
        extentTest.get().log(LogStatus.INFO, requestWriter.toString());
        extentTest.get().log(LogStatus.INFO, "Response : " + response.asPrettyString());

        String bookingId = createBookingResponse.getBookingid();

        //Delete the created booking
        RequestSpecification deleteRequest = RestAssured.given();
        deleteRequest.headers(headers);
        deleteRequest.pathParam("id",bookingId);
        deleteRequest.basePath(basePathDeleteBooking);

        deleteRequest.when().delete().then().statusCode(HttpStatus.SC_CREATED);

        //Fetch booking details using get request
        Response getBookingResponse = new GetBookingDetails().getHotelBookingToVerifyDeleteRequest(bookingId);

        //Verifying the booking details
        softAssert.assertEquals(getBookingResponse.getStatusCode(),HttpStatus.SC_NOT_FOUND);

        softAssert.assertAll();

    }

}
