package mk.ukim.finki.emt.lab1_grb.repository;

import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
}
