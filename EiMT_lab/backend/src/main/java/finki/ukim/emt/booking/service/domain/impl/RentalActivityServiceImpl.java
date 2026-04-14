package finki.ukim.emt.booking.service.domain.impl;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import finki.ukim.emt.booking.model.dto.PopularAccommodationDto;
import finki.ukim.emt.booking.model.dto.PopularHostDto;
import finki.ukim.emt.booking.repository.RentalActivityRepository;
import finki.ukim.emt.booking.service.domain.RentalActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RentalActivityServiceImpl implements RentalActivityService {
    private final RentalActivityRepository rentalActivityRepository;

    public RentalActivityServiceImpl(RentalActivityRepository rentalActivityRepository) {
        this.rentalActivityRepository = rentalActivityRepository;
    }

    @Override
    public Page<RentalActivity> findAll(Pageable pageable) {
        return rentalActivityRepository.findAll(pageable);
    }

    @Override
    public Page<RentalActivity> findByAccommodationId(Long accommodationId, Pageable pageable) {
        return rentalActivityRepository.findByAccommodationId(accommodationId, pageable);
    }

    @Override
    public Page<PopularAccommodationDto> findMostPopularAccommodations(Pageable pageable) {
        return rentalActivityRepository.findMostPopularAccommodations(pageable)
                .map(PopularAccommodationDto::from);
    }

    @Override
    public Page<PopularHostDto> findMostPopularHosts(Pageable pageable) {
        return rentalActivityRepository.findMostPopularHosts(pageable)
                .map(PopularHostDto::from);
    }
}

