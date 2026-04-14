package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> findWithHostAndCountryById(Long id);

    List<Accommodation> findAll();

    Page<Accommodation> findAllWithFilters(Category category, Long hostId,
                                           String countryName, Integer minRooms,
                                           Boolean hasAvailableRooms, Pageable pageable);

    Page<ShortAccommodationProjection> findAllShortProjections(Pageable pageable);

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> markAsRented(Long id);

    List<LongAccommodationProjection> findAllLongProjections();
}
