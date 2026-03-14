package mk.ukim.finki.emt.lab1_grb.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.lab1_grb.model.dto.CreateHostDto;
import mk.ukim.finki.emt.lab1_grb.model.dto.DisplayHostDto;
import mk.ukim.finki.emt.lab1_grb.model.exception.ResourceNotFoundException;
import mk.ukim.finki.emt.lab1_grb.service.application.HostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@AllArgsConstructor
public class HostController {

    private final HostApplicationService hostApplicationService;

    @GetMapping
    public List<DisplayHostDto> getAll() {
        return hostApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public DisplayHostDto getById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Host", id));
    }

    @PostMapping
    public ResponseEntity<DisplayHostDto> create(@Valid @RequestBody CreateHostDto dto) {
        DisplayHostDto created = hostApplicationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public DisplayHostDto update(@PathVariable Long id,
                                 @Valid @RequestBody CreateHostDto dto) {
        return hostApplicationService.update(id, dto)
                .orElseThrow(() -> new ResourceNotFoundException("Host", id));
    }

    @DeleteMapping("/{id}")
    public DisplayHostDto delete(@PathVariable Long id) {
        return hostApplicationService.deleteById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Host", id));
    }
}

