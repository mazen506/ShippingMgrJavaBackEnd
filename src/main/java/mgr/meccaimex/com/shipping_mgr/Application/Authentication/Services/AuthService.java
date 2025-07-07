package mgr.meccaimex.com.shipping_mgr.Application.Authentication.Services;

import lombok.AllArgsConstructor;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Common.Jwt;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.LoginRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.LoginResponse;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.User;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId).orElse(null);

    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLoginId(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByLoginId(request.getLoginId()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(accessToken, refreshToken);
    }

    public Jwt refreshAccessToken(String refreshToken) {
        var jwt = jwtService.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        return jwtService.generateAccessToken(user);
    }
}