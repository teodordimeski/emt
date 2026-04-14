package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.views.AccommodationInfoView;
import finki.ukim.emt.booking.model.enums.Category;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayAccommodationInfoViewDto(
    Long id,
    String name,
    Category category,
    Integer numRooms,
    Boolean rented,
    String hostName,
    String hostSurname,
    String countryName,
    LocalDateTime createdAt
) {
    public static DisplayAccommodationInfoViewDto from(AccommodationInfoView view) {
        return new DisplayAccommodationInfoViewDto(
            view.getId(),
            view.getName(),
            view.getCategory(),
            view.getNumRooms(),
            view.getRented(),
            view.getHostName(),
            view.getHostSurname(),
            view.getCountryName(),
            view.getCreatedAt()
        );
    }

    public static List<DisplayAccommodationInfoViewDto> from(List<AccommodationInfoView> views) {
        return views.stream()
            .map(DisplayAccommodationInfoViewDto::from)
            .toList();
    }
}

