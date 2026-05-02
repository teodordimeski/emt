package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.User;

public record RegisterUserRequestDto(
    String username,
    String password
) {
    public User toUser() {
        return new User(username, password);
    }
}

