package DTOFactories;

import DTOs.BookingDates;
import DTOs.BookingPayloadDTO;

import java.util.ArrayList;

import static helpers.Constants.*;

public class BookingPayloadDTOFactory {

    public BookingPayloadDTO createBookingPayloadDTOFactoryWithDataProviderTestData(ArrayList<String> testData) {

        BookingPayloadDTO payload = new BookingPayloadDTO();

        payload.setFirstname(testData.get(0));
        payload.setLastname(testData.get(1));

        if (testData.get(2) == null) {
            payload.setTotalprice(Float.parseFloat(TOTAL_PRICE));
        } else {
            payload.setTotalprice(Float.parseFloat(testData.get(2)));
        }
        if (testData.get(3) == null) {
            payload.setDepositpaid(true);
        } else {
            payload.setDepositpaid(Boolean.parseBoolean(testData.get(3)));
        }

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin(testData.get(4));
        bookingdates.setCheckout(testData.get(5));

        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(testData.get(6));


        return payload;

    }

    public BookingPayloadDTO createBookingPayloadDTOFactoryWithConstantTestData() {

        BookingPayloadDTO payload = new BookingPayloadDTO();
        payload.setFirstname(FIRST_NAME);
        payload.setLastname(LAST_NAME);
        payload.setTotalprice(Float.parseFloat(TOTAL_PRICE));
        payload.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin(CHECK_IN_DATE);
        bookingdates.setCheckout(CHECK_OUT_DATE);

        payload.setBookingdates(bookingdates);
        payload.setAdditionalneeds(ADDITIONAL_NEEDS);

        return payload;

    }
}
