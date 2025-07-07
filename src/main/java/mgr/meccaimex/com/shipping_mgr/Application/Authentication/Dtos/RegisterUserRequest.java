package mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String loginId;
    private String password;
    private String name;
}
