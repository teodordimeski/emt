package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.domain.Country;
import finki.ukim.emt.booking.model.domain.Host;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> delete(Long id);

    Accommodation rent(Long id);

    List<Accommodation> findAllByRented(boolean isRented);

    List<LongAccommodationProjection> findAllLongProjections();

    List<ShortAccommodationProjection> findAllShortProjections();

    Page<Accommodation> findAllPaged(Category category, Host host, Country hostCountry, int numRooms, boolean hasFreeRooms, int pageNumber, int pageSize);
}
