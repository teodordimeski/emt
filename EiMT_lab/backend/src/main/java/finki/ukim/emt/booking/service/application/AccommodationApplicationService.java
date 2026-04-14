package finki.ukim.emt.booking.service.application;

import finki.ukim.emt.booking.model.dto.CreateAccommodationDto;
import finki.ukim.emt.booking.model.dto.DisplayAccommodationDto;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    DisplayAccommodationDto findById(Long id);

    DisplayAccommodationDto findWithHostAndCountryById(Long id);

    List<DisplayAccommodationDto> findAll();

    Page<DisplayAccommodationDto> findAllWithFilters(Category category, Long hostId,
                                                     String countryName, Integer minRooms,
                                                     Boolean hasAvailableRooms, Pageable pageable);

    Page<ShortAccommodationProjection> findAllShortProjections(Pageable pageable);

    DisplayAccommodationDto create(CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> deleteById(Long id);

    Optional<DisplayAccommodationDto> markAsRented(Long id);

    List<LongAccommodationProjection> findAllLongProjections();


}
