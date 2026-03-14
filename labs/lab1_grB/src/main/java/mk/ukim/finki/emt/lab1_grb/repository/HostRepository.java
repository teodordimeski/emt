package mk.ukim.finki.emt.lab1_grb.repository;

import mk.ukim.finki.emt.lab1_grb.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host,Long> {
}
