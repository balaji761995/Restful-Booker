package steps;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    Properties prop;
    FileInputStream propFilePath;
    Header header;
    String userName, password;

    @BeforeSuite
    public void init() throws IOException {

        propFilePath = new FileInputStream("/Users/admin/IdeaProjects/Restful-Booker/config.properties");
        prop = new Properties();
        prop.load(propFilePath);
        RestAssured.baseURI = prop.getProperty("baseURI");
        userName = prop.getProperty("userName");
        password = prop.getProperty("password");

    }

    @BeforeSuite
    public void createHeaders() {

        header = new Header("Content-Type", "application/json");

    }

}
