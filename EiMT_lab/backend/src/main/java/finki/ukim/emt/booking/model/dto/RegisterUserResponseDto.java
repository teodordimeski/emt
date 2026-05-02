package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.User;
import finki.ukim.emt.booking.model.enums.Role;

public record RegisterUserResponseDto(
    String username,
    Role role
) {
    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
            user.getUsername(),
            user.getRole()
        );
    }
}

