package DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingResponseDTO {

    @JsonProperty
    private String bookingid;

    @JsonProperty
    private BookingPayloadDTO booking;

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPayloadDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingPayloadDTO booking) {
        this.booking = booking;
    }
}
