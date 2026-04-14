package finki.ukim.emt.booking.service.application.impl;

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
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService,
                                               HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public DisplayAccommodationDto findById(Long id) {
        return accommodationService
                .findById(id)
                .map(DisplayAccommodationDto::from)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation with id " + id + " not found."));
    }

    @Override
    public DisplayAccommodationDto findWithHostAndCountryById(Long id) {
        return accommodationService
                .findWithHostAndCountryById(id)
                .map(DisplayAccommodationDto::from)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation with id " + id + " not found."));
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Page<DisplayAccommodationDto> findAllWithFilters(Category category, Long hostId,
                                                            String countryName, Integer minRooms,
                                                            Boolean hasAvailableRooms, Pageable pageable) {
        return accommodationService
                .findAllWithFilters(category, hostId, countryName, minRooms, hasAvailableRooms, pageable)
                .map(DisplayAccommodationDto::from);
    }

    @Override
    public Page<ShortAccommodationProjection> findAllShortProjections(Pageable pageable) {
        return accommodationService.findAllShortProjections(pageable);
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto dto) {
        Host host = hostService.findById(dto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host with id " + dto.hostId() + " not found."));
        return DisplayAccommodationDto.from(accommodationService.create(dto.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto dto) {
        Host host = hostService.findById(dto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host with id " + dto.hostId() + " not found."));
        return accommodationService.update(id, dto.toAccommodation(host)).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService.deleteById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<LongAccommodationProjection> findAllLongProjections() {
        return accommodationService.findAllLongProjections();
    }
}
