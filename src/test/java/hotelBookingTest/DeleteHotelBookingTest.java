package hotelBookingTest;

import DTOFactories.BookingPayloadDTOFactory;
import DTOs.BookingPayloadDTO;
import DTOs.CreateBookingResponseDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import steps.BaseTest;
import steps.DataProviders;
import steps.GetBookingDetails;

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
        CreateBookingResponseDTO response = RestAssured.given().spec(createRequest).when().log().everything().post()
                .then().log().everything().statusCode(HttpStatus.SC_OK).extract().as(CreateBookingResponseDTO.class);

        String bookingId = response.getBookingid();

        //Delete the created booking
        RequestSpecification deleteRequest = RestAssured.given();
        deleteRequest.headers(headers);
        deleteRequest.pathParam("id",bookingId);
        deleteRequest.basePath(basePathDeleteBooking);

        deleteRequest.when().log().everything().delete()
                .then().log().everything().statusCode(HttpStatus.SC_CREATED);

        //Fetch booking details using get request
        Response getBookingResponse = new GetBookingDetails().getHotelBookingToVerifyDeleteRequest(bookingId);

        //Verifying the booking details
        softAssert.assertEquals(getBookingResponse.getStatusCode(),HttpStatus.SC_NOT_FOUND);

        softAssert.assertAll();

    }

}
