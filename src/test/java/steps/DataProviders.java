package steps;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static helpers.Constants.HOTEL_BOOKING_TEST_DATA;
import static helpers.ReadDataFromExcel.readTestDataFromExcel;

public class DataProviders {

    @DataProvider(name = "createHotelBookingTestData")
    public static Object[] createHotelBookingTestData() throws IOException {
        return readTestDataFromExcel(HOTEL_BOOKING_TEST_DATA, "createHotelBooking");
    }

    @DataProvider(name = "partialHotelBookingTestData")
    public static Object[] partialHotelBookingTestData() throws IOException {
        return readTestDataFromExcel(HOTEL_BOOKING_TEST_DATA, "partialUpdateBooking");
    }

}
