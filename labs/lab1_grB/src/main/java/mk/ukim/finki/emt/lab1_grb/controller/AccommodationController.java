package mk.ukim.finki.emt.lab1_grb.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt.lab1_grb.model.exception.ResourceNotFoundException;
import mk.ukim.finki.emt.lab1_grb.service.application.AccommodationApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@AllArgsConstructor
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;

    @GetMapping
    public List<DisplayAccommodationDto> getAll() {
        return accommodationApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public DisplayAccommodationDto getById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", id));
    }

    @PostMapping
    public ResponseEntity<DisplayAccommodationDto> create(@Valid @RequestBody CreateAccommodationDto dto) {
        DisplayAccommodationDto created = accommodationApplicationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public DisplayAccommodationDto update(@PathVariable Long id,
                                          @Valid @RequestBody CreateAccommodationDto dto) {
        return accommodationApplicationService.update(id, dto)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", id));
    }

    @DeleteMapping("/{id}")
    public DisplayAccommodationDto delete(@PathVariable Long id) {
        return accommodationApplicationService.deleteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", id));
    }

    @PostMapping("/{id}/rent")
    public DisplayAccommodationDto markAsRented(@PathVariable Long id) {
        return accommodationApplicationService.markAsRented(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation", id));
    }
}

