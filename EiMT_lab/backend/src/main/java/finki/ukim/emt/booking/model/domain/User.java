package finki.ukim.emt.booking.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import finki.ukim.emt.booking.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseAuditableEntity implements UserDetails {
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.ROLE_USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }
}

