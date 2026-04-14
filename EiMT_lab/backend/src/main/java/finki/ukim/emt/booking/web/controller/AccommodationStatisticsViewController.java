package finki.ukim.emt.booking.web.controller;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationStatisticsDto;
import finki.ukim.emt.booking.service.application.AccommodationStatisticsViewApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations/stats")
@Tag(name = "Accommodation Statistics", description = "Endpoints for viewing aggregated accommodation statistics")
public class AccommodationStatisticsViewController {
    private final AccommodationStatisticsViewApplicationService accommodationStatisticsViewApplicationService;

    public AccommodationStatisticsViewController(AccommodationStatisticsViewApplicationService accommodationStatisticsViewApplicationService) {
        this.accommodationStatisticsViewApplicationService = accommodationStatisticsViewApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get accommodation statistics by category", 
               description = "Retrieve aggregated statistics: total accommodations, total rooms, average rooms per accommodation")
    public ResponseEntity<List<DisplayAccommodationStatisticsDto>> findAll() {
        return ResponseEntity.ok(accommodationStatisticsViewApplicationService.findAll());
    }
}

