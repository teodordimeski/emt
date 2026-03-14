package mk.ukim.finki.emt.lab1_grb.service.application.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.exception.ResourceNotFoundException;
import mk.ukim.finki.emt.lab1_grb.repository.HostRepository;
import mk.ukim.finki.emt.lab1_grb.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab1_grb.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccomodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostRepository hostRepository;

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public DisplayAccommodationDto create(CreateAccommodationDto createAccommodationDto) {
        Host host = hostRepository.findById(createAccommodationDto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host", createAccommodationDto.hostId()));

        Accommodation accommodation = createAccommodationDto.toAccommodation(host);
        return DisplayAccommodationDto.from(accommodationService.create(accommodation));
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Host host = hostRepository.findById(createAccommodationDto.hostId())
                .orElseThrow(() -> new ResourceNotFoundException("Host", createAccommodationDto.hostId()));

        Accommodation accommodation = createAccommodationDto.toAccommodation(host);
        return accommodationService.update(id, accommodation).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> deleteById(Long id) {
        return accommodationService.deleteById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }
}

