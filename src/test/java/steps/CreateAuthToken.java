package steps;

import DTOs.AuthCredentialsDTO;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class CreateAuthToken extends BaseTest {

    String basePath = "/auth";

    public String createAuthToken() {

        AuthCredentialsDTO authCreds = new AuthCredentialsDTO();
        authCreds.setUsername(userName);
        authCreds.setPassword(password);

        String token = RestAssured.given().header("Content-Type","application/json").body(authCreds).basePath(basePath)
                .when().log().everything().post()
                .then().log().everything().extract().jsonPath().get("token").toString();

        return token;

    }

}
