package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.Accommodation;
import finki.ukim.emt.booking.model.enums.Category;
import finki.ukim.emt.booking.model.enums.Condition;
import finki.ukim.emt.booking.model.domain.Host;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccommodationDto(
    @NotBlank(message = "Name is required.")
    String name,
    @NotNull(message = "Category is required.")
    Category category,
    @NotNull(message = "Host ID is required.")
    Long hostId,
    @NotNull(message = "Condition is required.")
    Condition condition,
    @NotNull(message = "Number of rooms is required.")
    @Positive
    Integer numRooms
) {
    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, condition, numRooms);
    }
}
