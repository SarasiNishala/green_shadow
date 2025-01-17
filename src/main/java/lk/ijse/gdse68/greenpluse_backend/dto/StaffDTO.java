package lk.ijse.gdse68.greenpluse_backend.dto;

import lk.ijse.gdse68.greenpluse_backend.customObj.StaffResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffResponse {
    private int staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String joinedDate;
    private String dOB;
    private String buildingNo;
    private String lane;
    private String mainCity;
    private String mainState;
    private String postalCode;
    private String contactNo;
    private String email;
    private String role;
}
