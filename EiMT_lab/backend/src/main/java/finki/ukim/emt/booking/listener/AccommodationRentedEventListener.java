package finki.ukim.emt.booking.listener;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import finki.ukim.emt.booking.model.events.AccommodationRentedEvent;
import finki.ukim.emt.booking.repository.RentalActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AccommodationRentedEventListener {
    private final RentalActivityRepository rentalActivityRepository;

    public AccommodationRentedEventListener(RentalActivityRepository rentalActivityRepository) {
        this.rentalActivityRepository = rentalActivityRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void onAccommodationRented(AccommodationRentedEvent event) {
        log.info("[ASYNC - thread: {}] Processing accommodation rental event for accommodation: '{}'", 
            Thread.currentThread().getName(), event.getAccommodation().getName());

        // Create activity log entry
        RentalActivity activity = new RentalActivity(
            event.getAccommodation(),
            event.getAccommodation().getName(),
            "RENTED",
            LocalDateTime.now()
        );
        rentalActivityRepository.save(activity);

        log.info("Rental activity logged for accommodation: '{}'", event.getAccommodation().getName());

        // Check if accommodation is now fully booked
        if (event.getAccommodation().getRented() && event.getAccommodation().getNumRooms() <= 0) {
            log.warn("Accommodation '{}' is now fully booked (no free rooms available)", 
                event.getAccommodation().getName());
        }
    }
}

