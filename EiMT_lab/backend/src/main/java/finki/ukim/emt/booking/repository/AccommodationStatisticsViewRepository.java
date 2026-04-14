package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.views.AccommodationStatisticsView;
import finki.ukim.emt.booking.model.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatisticsViewRepository extends JpaRepository<AccommodationStatisticsView, Category> {
    @Modifying
    @Query(value = "CALL refresh_accommodation_statistics_view()", nativeQuery = true)
    void refresh();
}

