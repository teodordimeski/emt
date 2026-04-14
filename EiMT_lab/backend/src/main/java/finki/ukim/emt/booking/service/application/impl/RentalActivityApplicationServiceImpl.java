package finki.ukim.emt.booking.service.application.impl;

import finki.ukim.emt.booking.model.dto.DisplayRentalActivityDto;
import finki.ukim.emt.booking.model.dto.PopularAccommodationDto;
import finki.ukim.emt.booking.model.dto.PopularHostDto;
import finki.ukim.emt.booking.service.application.RentalActivityApplicationService;
import finki.ukim.emt.booking.service.domain.RentalActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RentalActivityApplicationServiceImpl implements RentalActivityApplicationService {
    private final RentalActivityService rentalActivityService;

    public RentalActivityApplicationServiceImpl(RentalActivityService rentalActivityService) {
        this.rentalActivityService = rentalActivityService;
    }

    @Override
    public Page<DisplayRentalActivityDto> findAll(Pageable pageable) {
        return rentalActivityService.findAll(pageable).map(DisplayRentalActivityDto::from);
    }

    @Override
    public Page<DisplayRentalActivityDto> findByAccommodationId(Long accommodationId, Pageable pageable) {
        return rentalActivityService.findByAccommodationId(accommodationId, pageable).map(DisplayRentalActivityDto::from);
    }

    @Override
    public Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable) {
        return rentalActivityService.findMostPopularAccommodations(pageable);
    }

    @Override
    public Page<PopularHostDto> findMostPopularHosts(Pageable pageable) {
        return rentalActivityService.findMostPopularHosts(pageable);
    }
}

