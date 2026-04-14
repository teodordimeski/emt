package finki.ukim.emt.booking.model.events;

import finki.ukim.emt.booking.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public record AccommodationRentedEvent(
        Long accommodationId,
        String accommodationName
) {
}