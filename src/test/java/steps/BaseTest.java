package steps;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    Properties prop;
    FileInputStream propFilePath;
    public static Headers headers;
    public static String userName, password;
    public static SoftAssert softAssert;

    @BeforeSuite
    public void init() throws IOException {

        propFilePath = new FileInputStream("/Users/admin/IdeaProjects/Restful-Booker/config.properties");
        prop = new Properties();
        prop.load(propFilePath);
        RestAssured.baseURI = prop.getProperty("baseURI");
        userName = prop.getProperty("userName");
        password = prop.getProperty("password");
        softAssert = new SoftAssert();

    }

    @BeforeTest
    public void createHeaders() {

        String authToken = new CreateAuthToken().createAuthToken();

        Header header1 = new Header("Content-Type","application/json");
        Header header2 = new Header("Accept","application/json");
        Header header3 = new Header("Cookie","token="+authToken);

        List<Header> headerList = new ArrayList<>();
        headerList.add(header1);
        headerList.add(header2);
        headerList.add(header3);

        headers = new Headers(headerList);

    }

}
