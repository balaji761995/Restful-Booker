package steps;

import DTOs.AuthCredentialsDTO;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class AuthCreateToken extends BaseTest {

    String basePath = "/auth";

    @Test
    public void createAuthToken() {

        AuthCredentialsDTO authCreds = new AuthCredentialsDTO();
        authCreds.setUsername(userName);
        authCreds.setPassword(password);

        String token = RestAssured.given().header(header).body(authCreds).basePath(basePath)
                .when().log().everything().post()
                .then().log().everything().extract().jsonPath().get("token").toString();

        //return token;

    }

    @Test
    public void test(){
        System.out.println("test");
    }
}
