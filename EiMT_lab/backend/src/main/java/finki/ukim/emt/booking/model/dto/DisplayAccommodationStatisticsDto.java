package finki.ukim.emt.booking.model.dto;

import finki.ukim.emt.booking.model.views.AccommodationStatisticsView;
import java.math.BigDecimal;
import java.util.List;

public record DisplayAccommodationStatisticsDto(
    String category,
    Long totalAccommodations,
    Long totalRooms,
    BigDecimal averageRooms
) {
    public static DisplayAccommodationStatisticsDto from(AccommodationStatisticsView view) {
        return new DisplayAccommodationStatisticsDto(
            view.getCategory().name(),
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

