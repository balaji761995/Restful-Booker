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
        payload.setTotalprice(Float.parseFloat(testData.get(2)));
        payload.setDepositpaid(Boolean.parseBoolean(testData.get(3)));

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(testData.get(4));
        bookingDates.setCheckout(testData.get(5));

        payload.setBookingDates(bookingDates);
        payload.setAdditionalneeds(testData.get(6));

        return payload;

    }

    public BookingPayloadDTO createBookingPayloadDTOFactoryWithConstantTestData() {

        BookingPayloadDTO payload = new BookingPayloadDTO();
        payload.setFirstname(FIRST_NAME);
        payload.setLastname(LAST_NAME);
        payload.setTotalprice(Float.parseFloat(TOTAL_PRICE));
        payload.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(CHECK_IN_DATE);
        bookingDates.setCheckout(CHECK_OUT_DATE);

        payload.setBookingDates(bookingDates);
        payload.setAdditionalneeds(ADDITIONAL_NEEDS);

        return payload;

    }
}
