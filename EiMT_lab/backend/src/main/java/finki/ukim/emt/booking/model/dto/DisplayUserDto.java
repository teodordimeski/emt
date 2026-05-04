package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.User;
import finki.ukim.emt.booking.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayUserDto(
    Long id,
    String username,
    Role role,
    LocalDateTime createdAt
) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
            user.getId(),
            user.getUsername(),
            user.getRole(),
            user.getCreatedAt()
        );
    }

    public static List<DisplayUserDto> from(List<User> users) {
        return users.stream().map(DisplayUserDto::from).toList();
    }
}

