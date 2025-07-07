package mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Login Id is required")
    @Email
    private String loginId; //Email or Phone

    @NotBlank(message = "Password is required")
    private String password;
}
