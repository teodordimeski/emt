package mk.ukim.finki.emt.lab1_grb.repository;

import mk.ukim.finki.emt.lab1_grb.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
}
