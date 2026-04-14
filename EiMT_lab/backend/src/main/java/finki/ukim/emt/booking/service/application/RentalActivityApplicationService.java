package finki.ukim.emt.booking.service.application;

import finki.ukim.emt.booking.model.dto.DisplayRentalActivityDto;
import finki.ukim.emt.booking.model.dto.PopularAccommodationDto;
import finki.ukim.emt.booking.model.dto.PopularHostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalActivityApplicationService {
    Page<DisplayRentalActivityDto> findAll(Pageable pageable);

    Page<DisplayRentalActivityDto> findByAccommodationId(Long accommodationId, Pageable pageable);

    Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable);

    Page<PopularHostDto> findMostPopularHosts(Pageable pageable);
}

