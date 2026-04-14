package finki.ukim.emt.booking.web.controller;

import finki.ukim.emt.booking.model.dto.CreateAccommodationDto;
import finki.ukim.emt.booking.model.dto.DisplayAccommodationDto;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.service.application.AccommodationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accommodations")
@Tag(name = "Accommodations", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all accommodations", description = "Retrieve all accommodations without pagination")
    public ResponseEntity<List<DisplayAccommodationDto>> findAll() {
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get accommodation by ID", description = "Retrieve a specific accommodation with optimized entity graph loading")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get accommodations with pagination", description = "Retrieve accommodations with pagination and sorting support")
    public ResponseEntity<Page<DisplayAccommodationDto>> findAllPaginated(
            @Parameter(description = "Page number (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field (name or createdAt)") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        // Return paginated accommodations for available ones (rented = false)
        List<DisplayAccommodationDto> allAccommodations = accommodationApplicationService.findAllByRented(false);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allAccommodations.size());
        List<DisplayAccommodationDto> pageContent = allAccommodations.subList(start, end);
        Page<DisplayAccommodationDto> result = new org.springframework.data.domain.PageImpl<>(pageContent, pageable, allAccommodations.size());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    @Operation(summary = "Create new accommodation", description = "Create a new accommodation")
    public ResponseEntity<DisplayAccommodationDto> create(@RequestBody @Valid CreateAccommodationDto createAccommodationDto) {
        return ResponseEntity.ok(accommodationApplicationService.create(createAccommodationDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update accommodation", description = "Update an existing accommodation")
    public ResponseEntity<DisplayAccommodationDto> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateAccommodationDto createAccommodationDto) {
        return accommodationApplicationService.update(id, createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete accommodation", description = "Delete an accommodation by ID")
    public ResponseEntity<DisplayAccommodationDto> delete(@PathVariable Long id) {
        return accommodationApplicationService.delete(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/rent/{id}")
    @Operation(summary = "Rent accommodation", description = "Mark an accommodation as rented and publish event")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationApplicationService.rent(id));
    }

    @GetMapping("/rented/{isRented}")
    @Operation(summary = "Get accommodations by rental status", description = "Retrieve accommodations filtered by rental status")
    public ResponseEntity<List<DisplayAccommodationDto>> findByRental(@PathVariable boolean isRented) {
        return ResponseEntity.ok(accommodationApplicationService.findAllByRented(isRented));
    }

}
