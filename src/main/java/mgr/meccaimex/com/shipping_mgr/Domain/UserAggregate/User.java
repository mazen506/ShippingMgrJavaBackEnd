package mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate;

import jakarta.persistence.*;
import lombok.*;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Interfaces.IBranchEntity;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Models.AggregateRoot;
import mgr.meccaimex.com.shipping_mgr.Domain.Common.Models.BranchEntity;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.Entities.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User extends AggregateRoot<Long>
                        implements IBranchEntity{

    public User(Long aLong) {
        super(aLong);
    }
    public User(String email, String phoneNumber, String name, String password) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private String address;

    private String locale;

    private String photo;

    private String phoneNumber;

    private boolean confirmed;

    private int failedAttempt;

    private boolean accessLocked;

    private LocalDateTime lockTime;

    public String refreshToken;

    public LocalDateTime refreshTokenExpireTime;

    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role> roles = new HashSet<>();

    private Integer branchId;

    @Override
    public Integer getBranchId() {
        return branchId;
    }

}