package lk.ijse.gdse68.greenpluse_backend.service;


import lk.ijse.gdse68.greenpluse_backend.customObj.VehicleResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void addVehicle(VehicleDTO vehicleDTO);

    void updateVehicle(VehicleDTO vehicleDTO, int vehicleCode);

    List<VehicleDTO> getAllVehicle();

    VehicleResponse getSelectedVehicle(int vehicleCode);

    void deleteVehi(int vehicleCode);
}
