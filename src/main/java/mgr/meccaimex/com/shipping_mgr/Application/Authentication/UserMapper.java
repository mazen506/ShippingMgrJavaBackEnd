package mgr.meccaimex.com.shipping_mgr.Application.Authentication;

import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos.RegisterUserRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.UpdateUserRequest;
import mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos.UserDto;
import mgr.meccaimex.com.shipping_mgr.Domain.UserAggregate.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
