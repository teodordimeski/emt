package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.domain.RentalActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalActivityRepository extends JpaRepository<RentalActivity, Long> {
    Page<RentalActivity> findByAccommodationId(Long accommodationId, Pageable pageable);

    Page<RentalActivity> findAll(Pageable pageable);

    @Query("""
        select ra.accommodationName, count(ra) as rentCount
        from RentalActivity ra
        where ra.activityType = 'ACCOMMODATION_RENTED'
        group by ra.accommodationName
        order by rentCount desc
        """)
    Page<Object[]> findMostPopularAccommodations(Pageable pageable);

    @Query(value = """
        select h.name || ' ' || h.surname as hostName, count(ra.id) as rentCount
        from rental_activities ra
        join accommodations a on a.id = ra.accommodation_id
        join hosts h on h.id = a.host_id
        where ra.activity_type = 'ACCOMMODATION_RENTED'
        group by h.name, h.surname
        order by rentCount desc
        """, nativeQuery = true)
    Page<Object[]> findMostPopularHosts(Pageable pageable);
}

