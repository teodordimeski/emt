package mk.ukim.finki.emt.lab1_grb.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import mk.ukim.finki.emt.lab1_grb.model.domain.*;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;

public record CreateAccommodationDto(
        @NotBlank
        String name,
        @NotNull
        Category category,
        @NotNull
        Long hostId,
        @Positive
        int rooms,
        @NotNull
        boolean rented,
        @NotNull
        State state
) {

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, rooms, rented, state);
    }

}
