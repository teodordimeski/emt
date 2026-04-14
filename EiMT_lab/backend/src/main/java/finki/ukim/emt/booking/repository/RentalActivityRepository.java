package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalActivityRepository extends JpaRepository<RentalActivity, Long> {
    Page<RentalActivity> findByAccommodationId(Long accommodationId, Pageable pageable);
    
    Page<RentalActivity> findAll(Pageable pageable);
}

