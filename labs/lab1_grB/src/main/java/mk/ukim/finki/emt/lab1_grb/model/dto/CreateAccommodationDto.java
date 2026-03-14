package mk.ukim.finki.emt.lab1_grb.model.dto;

import jakarta.validation.constraints.Positive;
import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import mk.ukim.finki.emt.lab1_grb.model.domain.Category;
import mk.ukim.finki.emt.lab1_grb.model.domain.Host;

public record CreateAccommodationDto(
        String name,
        Category category,
        Long hostId,
        @Positive
        int rooms
) {

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, rooms);
    }

}
