package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.views.AccommodationInfoView;
import java.util.List;

public record DisplayAccommodationInfoViewDto(
    Long id,
    String accommodationName,
    String category,
    Integer numRooms,
    Boolean rented,
    String hostFullName,
    String countryName
) {
    public static DisplayAccommodationInfoViewDto from(AccommodationInfoView view) {
        return new DisplayAccommodationInfoViewDto(
            view.getId(),
            view.getName(),
            view.getCategory().name(),
            view.getNumRooms(),
            view.getRented(),
            view.getHostName() + " " + view.getHostSurname(),
            view.getCountryName()
        );
    }

    public static List<DisplayAccommodationInfoViewDto> from(List<AccommodationInfoView> views) {
        return views.stream()
            .map(DisplayAccommodationInfoViewDto::from)
            .toList();
    }
}

