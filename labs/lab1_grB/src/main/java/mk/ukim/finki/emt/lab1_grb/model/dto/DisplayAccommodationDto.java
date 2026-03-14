package mk.ukim.finki.emt.lab1_grb.model.dto;

import mk.ukim.finki.emt.lab1_grb.model.domain.Accommodation;
import mk.ukim.finki.emt.lab1_grb.model.domain.Category;
import mk.ukim.finki.emt.lab1_grb.model.domain.State;

import java.util.List;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Long hostId,
        int rooms,
        State state,
        boolean rented
) {

    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getRooms(),
                accommodation.getState(),
                accommodation.isRented()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).toList();
    }

}