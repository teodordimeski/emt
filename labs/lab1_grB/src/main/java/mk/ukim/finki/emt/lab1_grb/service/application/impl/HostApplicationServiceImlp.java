package mk.ukim.finki.emt.lab1_grb.service.application.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.domain.Country;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateHostDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab1_grb.model.exception.ResourceNotFoundException;
import mk.ukim.finki.emt.lab1_grb.repository.CountryRepository;
import mk.ukim.finki.emt.lab1_grb.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab1_grb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostApplicationServiceImlp implements HostApplicationService {

    private final HostService hostService;
    private final CountryRepository countryRepository;
    
    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public DisplayHostDto create(CreateHostDto createHostDto ) {
        Country country = countryRepository.findById(createHostDto.countryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country", createHostDto.countryId()));

        return DisplayHostDto.from(hostService.create(createHostDto.toHost(country)));
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Country country = countryRepository.findById(createHostDto.countryId())
                .orElseThrow(() -> new ResourceNotFoundException("Country", createHostDto.countryId()));

        return hostService.update(id, createHostDto.toHost(country)).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> deleteById(Long id) {
        return hostService.deleteById(id).map(DisplayHostDto::from);
    }
}
