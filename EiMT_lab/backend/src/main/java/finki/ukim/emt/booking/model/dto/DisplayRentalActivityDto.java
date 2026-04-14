package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.domain.RentalActivity;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayRentalActivityDto(
    Long id,
    Long accommodationId,
    String accommodationName,
    String activityType,
    LocalDateTime eventTime,
    LocalDateTime createdAt
) {
    public static DisplayRentalActivityDto from(RentalActivity activity) {
        return new DisplayRentalActivityDto(
            activity.getId(),
            activity.getAccommodation().getId(),
            activity.getAccommodationName(),
            activity.getActivityType(),
            activity.getEventTime(),
            activity.getCreatedAt()
        );
    }

    public static List<DisplayRentalActivityDto> from(List<RentalActivity> activities) {
        return activities.stream()
            .map(DisplayRentalActivityDto::from)
            .toList();
    }
}

