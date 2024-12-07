package lk.ijse.gdse68.greenpluse_backend.dto;

import lk.ijse.gdse68.greenpluse_backend.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserResponse {
    private int userId;
    private String email;
    private String password;
    private String role;
}
