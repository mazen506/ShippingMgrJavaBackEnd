package mgr.meccaimex.com.shipping_mgr.Api.Controllers;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Common.JwtConfig;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.JwtResponse;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.LoginRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Services.AuthService;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.UserMapper;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8100/")
public class AuthController {
    private final UserMapper userMapper;
    private final AuthService authService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public JwtResponse Login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {

        var loginResult = authService.login(request);

        var refreshToken = loginResult.getRefreshToken().toString();
        var cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new JwtResponse(loginResult.getAccessToken().toString());

    }

/*    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
        @Valid @RequestBody RegisterUserRequest request,
        UriComponentsBuilder uriBuilder
    ) {

        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new RuntimeException("User with login id " + request.getLoginId() + " already exists");
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }*/

    @PostMapping("/refresh")
    public JwtResponse refresh(@CookieValue(value = "refreshToken") String refreshToken) {
        var accessToken = authService.refreshAccessToken(refreshToken);
        return new JwtResponse(accessToken.toString());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me() {

        var user = authService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);

    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}