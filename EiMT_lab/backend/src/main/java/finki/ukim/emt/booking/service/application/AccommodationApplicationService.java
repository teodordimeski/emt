package finki.ukim.emt.booking.service.application;

import finki.ukim.emt.booking.model.domain.Country;
import finki.ukim.emt.booking.model.domain.Host;
import finki.ukim.emt.booking.model.dto.CreateAccommodationDto;
import finki.ukim.emt.booking.model.dto.DisplayAccommodationDto;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    DisplayAccommodationDto create(CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> delete(Long id);

    DisplayAccommodationDto rent(Long id);

    List<DisplayAccommodationDto> findAllByRented(boolean isRented);

    List<LongAccommodationProjection> findAllLongProjections();

    List<ShortAccommodationProjection> findAllShortProjections();

    Page<DisplayAccommodationDto> findAllPaged(Category category, Host host, Country hostCountry, int numRooms, boolean hasFreeRooms, int pageNumber, int pageSize);

}
