package mk.ukim.finki.emt.lab1_grb.service.application;

import mk.ukim.finki.emt.lab1_grb.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateHostDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDto> findById(Long id);

    List<DisplayHostDto> findAll();

    DisplayHostDto create(CreateHostDto createHostDto);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    Optional<DisplayHostDto> deleteById(Long id);
}
