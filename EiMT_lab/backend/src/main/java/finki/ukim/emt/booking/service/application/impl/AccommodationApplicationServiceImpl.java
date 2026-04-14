package finki.ukim.emt.booking.service.application.impl;

import finki.ukim.emt.booking.model.domain.Country;
import finki.ukim.emt.booking.model.domain.Host;
import finki.ukim.emt.booking.model.dto.CreateAccommodationDto;
import finki.ukim.emt.booking.model.dto.DisplayAccommodationDto;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.exception.ResourceNotFoundException;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import finki.ukim.emt.booking.service.application.AccommodationApplicationService;
import finki.ukim.emt.booking.service.domain.AccommodationService;
import finki.ukim.emt.booking.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Host with id %d not found!", createAccommodationDto.hostId())));
        return DisplayAccommodationDto.from(accommodationService.create(createAccommodationDto.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Host host = hostService.findById(createAccommodationDto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Host with id %d not found!", createAccommodationDto.hostId())));
        return accommodationService.update(id, createAccommodationDto.toAccommodation(host))
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> delete(Long id) {
        return accommodationService.delete(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public DisplayAccommodationDto rent(Long id) {
        return DisplayAccommodationDto.from(accommodationService.rent(id));
    }

    @Override
    public List<DisplayAccommodationDto> findAllByRented(boolean isRented) {
        return DisplayAccommodationDto.from(accommodationService.findAllByRented(isRented));
    }

    @Override
    public List<LongAccommodationProjection> findAllLongProjections() {
        return accommodationService.findAllLongProjections();
    }

    @Override
    public List<ShortAccommodationProjection> findAllShortProjections() {
        return accommodationService.findAllShortProjections();
    }

    @Override
    public Page<DisplayAccommodationDto> findAllPaged(Category category, Host host, Country hostCountry, int numRooms, boolean hasFreeRooms, int pageNumber, int pageSize) {
        return accommodationService.findAllPaged(category, host, hostCountry, numRooms, hasFreeRooms, pageNumber, pageSize)
                .map(DisplayAccommodationDto::from);
    }

}
