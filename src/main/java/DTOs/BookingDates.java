package DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {

    @JsonProperty
    private String checkin;
    @JsonProperty
    private String checkout;

}
