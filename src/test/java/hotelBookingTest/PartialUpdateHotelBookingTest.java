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

import static helpers.Constants.PATCH;
import static steps.SendRequest.sendRequest;

public class PartialUpdateHotelBookingTest extends BaseTest {

    String basePathCreateBooking = "/booking";
    String basePathPartialUpdateBooking = "/booking/{id}";
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


    //Verification of partial update hotel booking end point
    @Test(dataProvider = "partialHotelBookingTestData", dataProviderClass = DataProviders.class)
    public void partialUpdateHotelBookingTest(ArrayList<String> testData) {

        String firstName = testData.get(0);
        String lastName = testData.get(1);
        String price = testData.get(2);
        String depositPaid = testData.get(3);
        String checkInDate = testData.get(4);
        String checkOutDate = testData.get(5);
        String additionalNeeds = testData.get(6);

        BookingPayloadDTO payload = new BookingPayloadDTOFactory().createBookingPayloadDTOFactoryWithDataProviderTestData(testData);

        //Creating Request specification
        RequestSpecification patchRequest = RestAssured.given();
        patchRequest.headers(headers);
        patchRequest.body(payload);
        patchRequest.pathParam("id", bookingId);
        patchRequest.basePath(basePathPartialUpdateBooking);

        //Patch Request to update hotel booking details
        sendRequest(patchRequest, PATCH).then().statusCode(HttpStatus.SC_OK);

        //Fetch booking details using get request
        BookingPayloadDTO getBookingResponse = new GetBookingDetails().getHotelBookingDetails(bookingId);

        //Verifying the partially updated booking details
        if (firstName != null) {
            softAssert.assertEquals(getBookingResponse.getFirstname(), firstName);
        }
        if (lastName != null) {
            softAssert.assertEquals(getBookingResponse.getLastname(), lastName);
        }
        if (price != null) {
            softAssert.assertEquals(getBookingResponse.getTotalprice(), Float.parseFloat(price));
        }
        if (depositPaid != null) {
            softAssert.assertEquals(getBookingResponse.getDepositpaid(), Boolean.parseBoolean(depositPaid));
        }
        if (checkInDate != null) {
            softAssert.assertEquals(getBookingResponse.getBookingdates().getCheckin(), checkInDate);
        }
        if (checkOutDate != null) {
            softAssert.assertEquals(getBookingResponse.getBookingdates().getCheckout(), checkOutDate);
        }
        if (additionalNeeds != null) {
            softAssert.assertEquals(getBookingResponse.getAdditionalneeds(), additionalNeeds);
        }

        softAssert.assertAll();


    }
}
