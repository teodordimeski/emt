package finki.ukim.emt.booking.service.application.impl;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationInfoViewDto;
import finki.ukim.emt.booking.service.application.AccommodationInfoViewApplicationService;
import finki.ukim.emt.booking.service.domain.AccommodationInfoViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationInfoViewApplicationServiceImpl implements AccommodationInfoViewApplicationService {
    private final AccommodationInfoViewService accommodationInfoViewService;

    public AccommodationInfoViewApplicationServiceImpl(AccommodationInfoViewService accommodationInfoViewService) {
        this.accommodationInfoViewService = accommodationInfoViewService;
    }

    @Override
    public List<DisplayAccommodationInfoViewDto> findAll() {
        return DisplayAccommodationInfoViewDto.from(accommodationInfoViewService.findAll());
    }
}

