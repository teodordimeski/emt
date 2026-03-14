package mk.ukim.finki.emt.lab1_grb.service.application.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.domain.Country;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateHostDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab1_grb.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab1_grb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostApplicationServiceImlp implements HostApplicationService {

    HostService hostService;
    
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
        return DisplayHostDto.from(hostService.create(createHostDto.toHost()));
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> deleteById(Long id) {
        return Optional.empty();
    }
}
