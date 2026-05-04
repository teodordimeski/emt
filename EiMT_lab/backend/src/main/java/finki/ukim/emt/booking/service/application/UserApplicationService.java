package finki.ukim.emt.booking.service.application;

import java.util.List;
import java.util.Optional;
import finki.ukim.emt.booking.model.dto.DisplayUserDto;
import finki.ukim.emt.booking.model.dto.LoginUserRequestDto;
import finki.ukim.emt.booking.model.dto.LoginUserResponseDto;
import finki.ukim.emt.booking.model.dto.RegisterUserRequestDto;
import finki.ukim.emt.booking.model.dto.RegisterUserResponseDto;

public interface UserApplicationService {
    List<DisplayUserDto> findAll();

    Optional<DisplayUserDto> findById(Long id);

    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}

