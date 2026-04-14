package finki.ukim.emt.booking.service.application;

import finki.ukim.emt.booking.model.dto.DisplayAccommodationInfoViewDto;

import java.util.List;

public interface AccommodationInfoViewApplicationService {
    List<DisplayAccommodationInfoViewDto> findAll();
}

