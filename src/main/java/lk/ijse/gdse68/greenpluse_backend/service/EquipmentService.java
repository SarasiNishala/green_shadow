package lk.ijse.gdse68.greenpluse_backend.service;


import lk.ijse.gdse68.greenpluse_backend.customObj.EquipResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void addEquipment(EquipmentDTO equipmentDTO);

    void updateEquipment(EquipmentDTO equipmentDTO, int equipmentId);

    List<EquipmentDTO> getAllEquip();

    EquipResponse getSelectedEquip(int equipmentId);

    void deleteEquip(int equipmentId);
}
