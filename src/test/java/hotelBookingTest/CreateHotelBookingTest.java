package hotelBookingTest;

import DTOFactories.BookingPayloadDTOFactory;
import DTOs.BookingPayloadDTO;
import DTOs.CreateBookingResponseDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import steps.BaseTest;
import steps.DataProviders;
import steps.GetBookingDetails;

import java.util.ArrayList;

public class CreateHotelBookingTest extends BaseTest {

    String basePath = "/booking";

    @Test(dataProvider = "createHotelBookingTestData",dataProviderClass = DataProviders.class)
    public void createHotelBookingTest(ArrayList<String> testData){

        String firstName = testData.get(0);
        String lastName = testData.get(1);
        String price = testData.get(2);
        String depositPaid = testData.get(3);
        String checkInDate = testData.get(4);
        String checkOutDate = testData.get(5);
        String additionalNeeds = testData.get(6);

        BookingPayloadDTO payload = new BookingPayloadDTOFactory().createBookingPayloadDTOFactoryWithDataProviderTestData(testData);

        //Creating Request specifications
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.addHeader("Content-Type", "application/json");
        requestSpec.setBody(payload);
        requestSpec.setBasePath(basePath);

        RequestSpecification request = requestSpec.build();

        //Post Request to create hotel booking
        CreateBookingResponseDTO response = RestAssured.given().spec(request).when().log().everything().post()
                .then().log().everything().statusCode(HttpStatus.SC_OK).extract().as(CreateBookingResponseDTO.class);

        String bookingId = response.getBookingid();

        //Fetch booking details using get request
        BookingPayloadDTO getBookingResponse = new GetBookingDetails().getHotelBookingDetails(bookingId);

        //Verifying the booking details
        softAssert.assertEquals(getBookingResponse.getFirstname(),firstName);
        softAssert.assertEquals(getBookingResponse.getLastname(),lastName);
        softAssert.assertEquals(getBookingResponse.getTotalprice(),Float.parseFloat(price));
        softAssert.assertEquals(getBookingResponse.getDepositpaid(), Boolean.parseBoolean(depositPaid));
        softAssert.assertEquals(getBookingResponse.getBookingDates().getCheckin(),checkInDate);
        softAssert.assertEquals(getBookingResponse.getBookingDates().getCheckout(),checkOutDate);
        softAssert.assertEquals(getBookingResponse.getAdditionalneeds(),additionalNeeds);

        softAssert.assertAll();

    }

}