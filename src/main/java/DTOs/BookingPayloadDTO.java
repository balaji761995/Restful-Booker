package DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingPayloadDTO {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private float totalprice;
    @JsonProperty
    private boolean depositpaid;
    @JsonProperty
    BookingDates BookingdatesObject;
    @JsonProperty
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdatesObject() {
        return BookingdatesObject;
    }

    public void setBookingdatesObject(BookingDates bookingdatesObject) {
        BookingdatesObject = bookingdatesObject;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
