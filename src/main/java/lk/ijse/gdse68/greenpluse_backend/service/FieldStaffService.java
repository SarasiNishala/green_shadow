package lk.ijse.gdse68.greenpluse_backend.service;

import lk.ijse.gdse68.greenpluse_backend.customObj.FieldStaffResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.FieldStaffDTO;

import java.util.List;

public interface FieldStaffService {
    void assignFieldToStaff(FieldStaffDTO fieldStaffDTO);

    List<FieldStaffDTO> getAllAssigns();

    FieldStaffResponse getSelectedAssign(int fieldStaffId);

    void deleteAssign(int fieldStaffId);
}
