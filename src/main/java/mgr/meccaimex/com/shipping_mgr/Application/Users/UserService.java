package mgr.meccaimex.com.shipping_mgr.Application.Users;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.RegisterUserRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.UserMapper;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.ChangePasswordRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.UpdateUserRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.UserDto;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Exceptions.DuplicateUserException;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Exceptions.UserNotFoundException;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Iterable<UserDto> getAllUsers(String sortBy) {
        if (!Set.of("name", "email").contains(sortBy))
            sortBy = "name";

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new DuplicateUserException();
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRole(Role.USER);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long userId, UpdateUserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userMapper.update(request, user);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AccessDeniedException("Password does not match");
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);

    }
}
