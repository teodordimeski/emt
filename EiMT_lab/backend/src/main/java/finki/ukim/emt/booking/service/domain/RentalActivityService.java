package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import finki.ukim.emt.booking.model.dto.PopularAccommodationDto;
import finki.ukim.emt.booking.model.dto.PopularHostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalActivityService {
    Page<RentalActivity> findAll(Pageable pageable);

    Page<RentalActivity> findByAccommodationId(Long accommodationId, Pageable pageable);

    Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable);

    Page<PopularHostDto> findMostPopularHosts(Pageable pageable);
}

