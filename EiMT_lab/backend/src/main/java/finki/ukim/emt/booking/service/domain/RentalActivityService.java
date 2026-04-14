package finki.ukim.emt.booking.service.domain;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalActivityService {
    Page<RentalActivity> findAll(Pageable pageable);
    
    Page<RentalActivity> findByAccommodationId(Long accommodationId, Pageable pageable);
}

