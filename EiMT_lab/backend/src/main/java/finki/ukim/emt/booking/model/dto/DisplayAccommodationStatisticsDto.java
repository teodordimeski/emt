package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.views.AccommodationStatisticsView;
import finki.ukim.emt.booking.model.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public record DisplayAccommodationStatisticsDto(
    Category category,
    Long totalAccommodations,
    Long totalRooms,
    BigDecimal avgRoomsPerAccommodation
) {
    public static DisplayAccommodationStatisticsDto from(AccommodationStatisticsView view) {
        return new DisplayAccommodationStatisticsDto(
            view.getCategory(),
            view.getTotalAccommodations(),
            view.getTotalRooms(),
            view.getAvgRoomsPerAccommodation()
        );
    }

    public static List<DisplayAccommodationStatisticsDto> from(List<AccommodationStatisticsView> views) {
        return views.stream()
            .map(DisplayAccommodationStatisticsDto::from)
            .toList();
    }
}

