package mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mgr.meccaimex.com.shipping_mgr.Application.Authentication.Common.Jwt;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
