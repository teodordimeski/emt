package finki.ukim.emt.booking.model.projection;

import finki.ukim.emt.booking.model.domain.Country;
import finki.ukim.emt.booking.model.enums.Category;

public interface LongAccommodationProjection {
    Long getId();
    String getName();
    Category getCategory();
    int getNumRooms();
    String getHostName();
    String getHostSurname();
    String getCountryName();
}
