package mk.ukim.finki.emt.lab1_grb.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodations")
public class Accommodation extends BaseAuditableEntity{

    //so BaseAuditableEntity imame id crated at i updated at
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Category category;

    @ManyToOne
    @JoinColumn(name = "host_id")
    Host host;

    @Column(nullable = false)
    int Rooms;
}
