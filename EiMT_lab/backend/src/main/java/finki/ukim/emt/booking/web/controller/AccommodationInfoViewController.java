package finki.ukim.emt.booking.web.controller;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationInfoViewDto;
import finki.ukim.emt.booking.service.application.AccommodationInfoViewApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations/summary")
@Tag(name = "Accommodation Views", description = "Endpoints for viewing accommodation information through database views")
public class AccommodationInfoViewController {
    private final AccommodationInfoViewApplicationService accommodationInfoViewApplicationService;

    public AccommodationInfoViewController(AccommodationInfoViewApplicationService accommodationInfoViewApplicationService) {
        this.accommodationInfoViewApplicationService = accommodationInfoViewApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all accommodations from view", description = "Retrieve accommodation information with host and country details")
    public ResponseEntity<List<DisplayAccommodationInfoViewDto>> findAll() {
        return ResponseEntity.ok(accommodationInfoViewApplicationService.findAll());
    }
}

