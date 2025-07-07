package mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String loginId;
    private String password;
    private String name;
}
