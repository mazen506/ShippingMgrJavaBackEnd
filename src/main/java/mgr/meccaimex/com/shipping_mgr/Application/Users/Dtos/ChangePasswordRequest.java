package mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
