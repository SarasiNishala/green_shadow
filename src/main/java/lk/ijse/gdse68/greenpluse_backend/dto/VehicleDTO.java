package lk.ijse.gdse68.greenpluse_backend.dto;

import lk.ijse.gdse68.greenpluse_backend.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleResponse {
    private int vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private int staffId;
}
