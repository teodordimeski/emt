package mk.ukim.finki.emt.lab1_grb.service.application;

import mk.ukim.finki.emt.lab1_grb.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    Optional<DisplayAccommodationDto> findById(Long id);

    List<DisplayAccommodationDto> findAll();

    DisplayAccommodationDto create(CreateAccommodationDto craateAccomodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto craateAccomodationDto);

    Optional<DisplayAccommodationDto> deleteById(Long id);

    Optional<DisplayAccommodationDto> markAsRented(Long id);
}
