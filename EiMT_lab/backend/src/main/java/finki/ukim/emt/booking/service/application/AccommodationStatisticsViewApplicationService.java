package finki.ukim.emt.booking.service.application;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationStatisticsDto;

import java.util.List;

public interface AccommodationStatisticsViewApplicationService {
    List<DisplayAccommodationStatisticsDto> findAll();
}

