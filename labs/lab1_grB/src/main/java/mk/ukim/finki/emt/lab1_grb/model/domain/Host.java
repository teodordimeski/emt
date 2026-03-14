package mk.ukim.finki.emt.lab1_grb.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hosts")
public class Host extends BaseAuditableEntity {
    //    BaseAuditableEntity dava id crated updated
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @ManyToOne
    @JoinColumn( name = "country_id")
    Country country;

}
