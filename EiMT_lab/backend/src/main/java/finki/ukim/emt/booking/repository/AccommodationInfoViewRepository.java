package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.views.AccommodationInfoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationInfoViewRepository extends JpaRepository<AccommodationInfoView, Long> {
}

