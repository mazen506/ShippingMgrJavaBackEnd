package mgr.meccaimex.com.shipping_mgr.Application.Authentication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}