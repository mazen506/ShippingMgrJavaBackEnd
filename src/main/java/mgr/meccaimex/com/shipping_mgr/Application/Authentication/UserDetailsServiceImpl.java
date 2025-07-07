package mgr.meccaimex.com.shipping_mgr.Application.Authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        var user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );

    }
}
