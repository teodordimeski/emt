package finki.ukim.emt.booking.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental_activities")
public class RentalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    private String accommodationName;

    @Column(nullable = false)
    private String activityType;

    @Column(nullable = false)
    private LocalDateTime eventTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (eventTime == null) {
            eventTime = LocalDateTime.now();
        }
    }

    public RentalActivity(Accommodation accommodation, String accommodationName, String activityType, LocalDateTime eventTime) {
        this.accommodation = accommodation;
        this.accommodationName = accommodationName;
        this.activityType = activityType;
        this.eventTime = eventTime;
    }
}

