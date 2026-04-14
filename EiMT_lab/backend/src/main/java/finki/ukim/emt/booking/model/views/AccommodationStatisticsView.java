package finki.ukim.emt.booking.model.views;

import finki.ukim.emt.booking.model.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Immutable
@Table(name = "accommodation_statistics_view")
public class AccommodationStatisticsView {
    @Id
    private Category category;

    private Long totalAccommodations;

    private Long totalRooms;

    private BigDecimal avgRoomsPerAccommodation;
}

