package finki.ukim.emt.booking.listener;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.domain.RentalActivity;
import finki.ukim.emt.booking.model.events.AccommodationRentedEvent;
import finki.ukim.emt.booking.repository.AccommodationRepository;
import finki.ukim.emt.booking.repository.RentalActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;


@Component
@Slf4j
public class AccommodationRentedEventListener {
    private final RentalActivityRepository rentalActivityRepository;
    private final AccommodationRepository accommodationRepository;

    public AccommodationRentedEventListener(RentalActivityRepository rentalActivityRepository,
                                            AccommodationRepository accommodationRepository) {
        this.rentalActivityRepository = rentalActivityRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onAccommodationRented(AccommodationRentedEvent event) {
        log.info("[ASYNC - thread: {}] Accommodation '{}' has been rented.",
                Thread.currentThread().getName(), event.accommodationName());

        accommodationRepository.findById(event.accommodationId()).ifPresent(accommodation -> {
            persistRentalActivity(accommodation, event.accommodationName(), "ACCOMMODATION_RENTED");

            if (accommodation.getNumRooms() == 0) {
                log.info("Accommodation '{}' is now fully occupied!", event.accommodationName());
                persistRentalActivity(accommodation, event.accommodationName(), "ACCOMMODATION_FULLY_OCCUPIED");
            }
        });
    }

    private void persistRentalActivity(Accommodation accommodation, String accommodationName, String activityType) {
        rentalActivityRepository.save(new RentalActivity(
                accommodation,
                accommodationName,
                activityType,
                LocalDateTime.now()
        ));
    }
}