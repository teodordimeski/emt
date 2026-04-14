package finki.ukim.emt.booking.service.application.impl;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationStatisticsDto;
import finki.ukim.emt.booking.service.application.AccommodationStatisticsViewApplicationService;
import finki.ukim.emt.booking.service.domain.AccommodationStatisticsViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationStatisticsViewApplicationServiceImpl implements AccommodationStatisticsViewApplicationService {
    private final AccommodationStatisticsViewService accommodationStatisticsViewService;

    public AccommodationStatisticsViewApplicationServiceImpl(AccommodationStatisticsViewService accommodationStatisticsViewService) {
        this.accommodationStatisticsViewService = accommodationStatisticsViewService;
    }

    @Override
    public List<DisplayAccommodationStatisticsDto> findAll() {
        return DisplayAccommodationStatisticsDto.from(accommodationStatisticsViewService.findAll());
    }
}

