package hotelBookingTest;

import DTOs.GetBookingIdsResponseDTO;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import steps.BaseTest;

import java.util.List;

import static helpers.Constants.GET;
import static steps.SendRequest.sendRequest;

public class GetHotelBookingIdsTest extends BaseTest {

    String basePath = "/booking";

    //Verification of get all hotel booking id's end point
    @Test
    public void getHotelBookingIdsTest() {

        RequestSpecification getBookingIdsRequest = RestAssured.given();
        getBookingIdsRequest.headers(headers);
        getBookingIdsRequest.basePath(basePath);

        List<GetBookingIdsResponseDTO> bookingIdsResponse = sendRequest(getBookingIdsRequest, GET)
                .then().statusCode(HttpStatus.SC_OK).extract().body().jsonPath().getList("", GetBookingIdsResponseDTO.class);

        softAssert.assertNotNull(bookingIdsResponse);

        softAssert.assertAll();
    }
}
