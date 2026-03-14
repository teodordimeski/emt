package mk.ukim.finki.emt.lab1_grb.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accommodations")
public class Accommodation extends BaseAuditableEntity{

    // so BaseAuditableEntity imame id, createdAt i updatedAt
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(nullable = false)
    private int rooms;

    @Column(nullable = false)
    private boolean rented = false;


    public Accommodation(String name, Category category, Host host, int rooms, boolean rented, State state) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.rooms = rooms;
        this.rented = rented;
        this.state = state;
    }
    


}
