package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @EntityGraph("accommodation-entity-graph")
    Page<Accommodation> findAll(Specification<Accommodation> filter, Pageable pageable);
    
    @EntityGraph("accommodation-entity-graph")
    Optional<Accommodation> findById(Long id);
    
    List<Accommodation> findAllByRented(boolean rented);

    @Query(value = "select id, name, category, num_rooms from accommodations", nativeQuery = true)
    List<ShortAccommodationProjection> findAllWithIdNameCategoryAndNumRooms();

    @Query(value = "select id, name, category, num_rooms, hosts.name as hostName, hosts.surname as hostSurname, countries.name as countryName from accommodations join hosts on accommodations.host_id=hosts.id join countries on countries.id=hosts.country_id", nativeQuery = true)
    List<LongAccommodationProjection> findAllWithIdNameCategorNumRoomsHostNameHostSurnameAndHostCountry();

}
