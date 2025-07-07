package mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.Entities;


import jakarta.persistence.*;
import lombok.*;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.User;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
