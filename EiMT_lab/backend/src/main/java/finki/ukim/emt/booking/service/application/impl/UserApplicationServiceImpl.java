package finki.ukim.emt.booking.service.application.impl;

import java.util.Optional;
import finki.ukim.emt.booking.helpers.JwtHelper;
import finki.ukim.emt.booking.model.domain.User;
import finki.ukim.emt.booking.model.dto.LoginUserRequestDto;
import finki.ukim.emt.booking.model.dto.LoginUserResponseDto;
import finki.ukim.emt.booking.model.dto.RegisterUserRequestDto;
import finki.ukim.emt.booking.model.dto.RegisterUserResponseDto;
import finki.ukim.emt.booking.service.application.UserApplicationService;
import finki.ukim.emt.booking.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto displayUserDto = RegisterUserResponseDto.from(user);
        return Optional.of(displayUserDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService
            .findByUsername(username)
            .map(RegisterUserResponseDto::from);
    }
}

