package steps;

import DTOs.BookingPayloadDTO;
import io.restassured.RestAssured;

public class GetBookingDetails extends BaseTest {

    public static String basePath = "booking/{id}";

    public static BookingPayloadDTO getHotelBookingDetails(String id){
        BookingPayloadDTO response = RestAssured.given().header(header).pathParam("id",id).basePath(basePath)
                .when().log().everything()
                .get()
                .then().log().everything()
                .statusCode(200).extract().as(BookingPayloadDTO.class);

        return response;
    }


}
