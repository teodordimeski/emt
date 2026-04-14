package finki.ukim.emt.booking.web.controller;

import finki.ukim.emt.booking.model.dto.DisplayRentalActivityDto;
import finki.ukim.emt.booking.service.application.RentalActivityApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental-activities")
@Tag(name = "Rental Activities", description = "Endpoints for managing and viewing rental activity logs")
public class RentalActivityController {
    private final RentalActivityApplicationService rentalActivityApplicationService;

    public RentalActivityController(RentalActivityApplicationService rentalActivityApplicationService) {
        this.rentalActivityApplicationService = rentalActivityApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all rental activities with pagination", 
               description = "Retrieve rental activity logs with pagination support")
    public ResponseEntity<Page<DisplayRentalActivityDto>> findAll(
            @Parameter(description = "Page number (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "eventTime"));
        return ResponseEntity.ok(rentalActivityApplicationService.findAll(pageable));
    }

    @GetMapping("/accommodation/{accommodationId}")
    @Operation(summary = "Get rental activities for a specific accommodation", 
               description = "Retrieve rental activity logs for a particular accommodation with pagination")
    public ResponseEntity<Page<DisplayRentalActivityDto>> findByAccommodationId(
            @Parameter(description = "Accommodation ID") @PathVariable Long accommodationId,
            @Parameter(description = "Page number (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "eventTime"));
        return ResponseEntity.ok(rentalActivityApplicationService.findByAccommodationId(accommodationId, pageable));
    }
}

