package steps;

import DTOs.BookingPayloadDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class GetBookingDetails extends BaseTest {

    public static String basePath = "booking/{id}";

    public BookingPayloadDTO getHotelBookingDetails(String id){
        BookingPayloadDTO response = RestAssured.given().headers(headers).pathParam("id",id).basePath(basePath)
                .when().log().everything()
                .get()
                .then().log().everything()
                .statusCode(HttpStatus.SC_OK).extract().as(BookingPayloadDTO.class);

        return response;
    }

    public Response getHotelBookingToVerifyDeleteRequest(String id){
        Response response = RestAssured.given().headers(headers).pathParam("id",id).basePath(basePath)
                .when().log().everything()
                .get()
                .then().log().everything()
                .statusCode(HttpStatus.SC_NOT_FOUND).extract().response();

        return response;
    }


}
