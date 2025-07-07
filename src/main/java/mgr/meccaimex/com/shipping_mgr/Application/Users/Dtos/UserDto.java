package mgr.meccaimex.com.shipping_mgr.Application.Users.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String locale;
    private String photo;
}
