package finki.ukim.emt.booking.repository;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.projection.LongAccommodationProjection;
import finki.ukim.emt.booking.model.projection.ShortAccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @EntityGraph(value = "accommodation-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Accommodation> findWithHostAndCountryById(Long id);

    @EntityGraph("accommodation-entity-graph")
    Optional<Accommodation> findById(Long id);

    @Query("""
        select a from Accommodation a
        join a.host h
        join h.country c
        where (:category is null or a.category = :category)
        and (:hostId is null or h.id = :hostId)
        and (:countryName is null or c.name = :countryName)
        and (:minRooms is null or a.numRooms >= :minRooms)
        and (:hasAvailableRooms is null or
            (:hasAvailableRooms = true and a.rented = false) or
            (:hasAvailableRooms = false and a.rented = true))
        """)
    Page<Accommodation> findAllWithFilters(
            @Param("category") Category category,
            @Param("hostId") Long hostId,
            @Param("countryName") String countryName,
            @Param("minRooms") Integer minRooms,
            @Param("hasAvailableRooms") Boolean hasAvailableRooms,
            Pageable pageable
    );

    @Query("""
        select a.id as id, a.name as name, a.category as category, a.numRooms as numRooms
        from Accommodation a
        """)
    Page<ShortAccommodationProjection> findAllShortProjections(Pageable pageable);

    @Query(value = "select id, name, category, num_rooms, hosts.name as hostName, hosts.surname as hostSurname, countries.name as countryName from accommodations join hosts on accommodations.host_id=hosts.id join countries on countries.id=hosts.country_id", nativeQuery = true)
    List<LongAccommodationProjection> findAllWithIdNameCategorNumRoomsHostNameHostSurnameAndHostCountry();

}
