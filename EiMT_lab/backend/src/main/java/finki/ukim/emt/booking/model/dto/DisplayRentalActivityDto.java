package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.RentalActivity;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayRentalActivityDto(
    Long id,
    String accommodationName,
    LocalDateTime eventTime,
    String eventType
) {
    public static DisplayRentalActivityDto from(RentalActivity activity) {
        return new DisplayRentalActivityDto(
            activity.getId(),
            activity.getAccommodationName(),
            activity.getEventTime(),
            activity.getActivityType()
        );
    }

    public static List<DisplayRentalActivityDto> from(List<RentalActivity> activities) {
        return activities.stream()
            .map(DisplayRentalActivityDto::from)
            .toList();
    }
}

