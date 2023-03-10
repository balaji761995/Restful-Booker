package hotelBookingTest;

import DTOFactories.BookingPayloadDTOFactory;
import DTOs.BookingPayloadDTO;
import DTOs.CreateBookingResponseDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.BaseTest;
import steps.DataProviders;
import steps.GetBookingDetails;

import java.util.ArrayList;

import static helpers.Constants.PUT;
import static steps.SendRequest.sendRequest;

public class UpdateHotelBookingTest extends BaseTest {

    String basePathCreateBooking = "/booking";
    String basePathUpdateBooking = "/booking/{id}";
    String bookingId;

    @BeforeMethod
    public void createHotelBooking() {

        BookingPayloadDTO payload = new BookingPayloadDTOFactory().createBookingPayloadDTOFactoryWithConstantTestData();

        //Creating Request specifications
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.addHeader("Content-Type", "application/json");
        requestSpec.setBody(payload);
        requestSpec.setBasePath(basePathCreateBooking);

        RequestSpecification request = requestSpec.build();

        //Post Request to create hotel booking
        CreateBookingResponseDTO response = RestAssured.given().spec(request).when().post()
                .then().statusCode(HttpStatus.SC_OK).extract().as(CreateBookingResponseDTO.class);

        bookingId = response.getBookingid();

        //Fetch booking details using get request
        new GetBookingDetails().getHotelBookingDetails(bookingId);

    }

    //Verification of update hotel booking end point
    @Test(dataProvider = "createHotelBookingTestData", dataProviderClass = DataProviders.class)
    public void updateHotelBookingTest(ArrayList<String> testData) {

        String firstName = testData.get(0);
        String lastName = testData.get(1);
        String price = testData.get(2);
        String depositPaid = testData.get(3);
        String checkInDate = testData.get(4);
        String checkOutDate = testData.get(5);
        String additionalNeeds = testData.get(6);

        BookingPayloadDTO payload = new BookingPayloadDTOFactory().createBookingPayloadDTOFactoryWithDataProviderTestData(testData);

        //Creating Request specification
        RequestSpecification putRequest = RestAssured.given();
        putRequest.headers(headers);
        putRequest.body(payload);
        putRequest.pathParam("id", bookingId);
        putRequest.basePath(basePathUpdateBooking);

        //Put Request to update hotel booking details
        sendRequest(putRequest, PUT).then().statusCode(HttpStatus.SC_OK);

        //Fetch booking details using get request
        BookingPayloadDTO getBookingResponse = new GetBookingDetails().getHotelBookingDetails(bookingId);

        //Verifying the booking details
        softAssert.assertEquals(getBookingResponse.getFirstname(), firstName);
        softAssert.assertEquals(getBookingResponse.getLastname(), lastName);
        softAssert.assertEquals(getBookingResponse.getTotalprice(), Float.parseFloat(price));
        softAssert.assertEquals(getBookingResponse.getDepositpaid(), Boolean.parseBoolean(depositPaid));
        softAssert.assertEquals(getBookingResponse.getBookingdates().getCheckin(), checkInDate);
        softAssert.assertEquals(getBookingResponse.getBookingdates().getCheckout(), checkOutDate);
        softAssert.assertEquals(getBookingResponse.getAdditionalneeds(), additionalNeeds);

        softAssert.assertAll();


    }

}
