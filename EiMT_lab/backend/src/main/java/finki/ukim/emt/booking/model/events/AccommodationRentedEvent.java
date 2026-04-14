package finki.ukim.emt.booking.model.events;

import finki.ukim.emt.booking.model.domain.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationRentedEvent extends ApplicationEvent {
    private final Accommodation accommodation;

    public AccommodationRentedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
