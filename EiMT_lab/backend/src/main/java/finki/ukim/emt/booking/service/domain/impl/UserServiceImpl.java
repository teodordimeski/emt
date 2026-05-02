package finki.ukim.emt.booking.service.domain.impl;

import java.util.Optional;
import finki.ukim.emt.booking.model.domain.User;
import finki.ukim.emt.booking.model.exception.IncorrectPasswordException;
import finki.ukim.emt.booking.model.exception.UserNotFoundException;
import finki.ukim.emt.booking.model.exception.UsernameAlreadyExistsException;
import finki.ukim.emt.booking.repository.UserRepository;
import finki.ukim.emt.booking.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UsernameAlreadyExistsException(user.getUsername());
        return userRepository.save(new User(
            user.getUsername(),
            passwordEncoder.encode(user.getPassword())
        ));
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IncorrectPasswordException();
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

