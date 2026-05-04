package finki.ukim.emt.booking.service.domain;

import java.util.List;
import java.util.Optional;
import finki.ukim.emt.booking.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User register(User user);

    User login(String username, String password);
}

