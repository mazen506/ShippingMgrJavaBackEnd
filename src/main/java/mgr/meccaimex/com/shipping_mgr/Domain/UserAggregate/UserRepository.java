package mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT Exists (select 1 FROM User u WHERE u.email = :loginId or u.phoneNumber = :loginId)")
    boolean existsByLoginId(String loginId);

    @Query("SELECT u FROM User u WHERE u.email = :loginId or u.phoneNumber = :loginId")
    Optional<User> findByLoginId(String loginId);

}
