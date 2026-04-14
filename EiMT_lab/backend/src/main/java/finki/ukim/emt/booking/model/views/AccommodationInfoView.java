package finki.ukim.emt.booking.model.views;

import finki.ukim.emt.booking.model.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Immutable
@Table(name = "accommodation_info_view")
public class AccommodationInfoView {
    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer numRooms;

    private Boolean rented;

    private String hostName;

    private String hostSurname;

    private String countryName;

    private LocalDateTime createdAt;
}

