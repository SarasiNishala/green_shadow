package lk.ijse.gdse68.greenpluse_backend.service;



import lk.ijse.gdse68.greenpluse_backend.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    void addStaff(StaffDTO staffDTO);

    void updateSatff(StaffDTO staffDTO, int staffId);

    StaffDTO getSelectedStaff(int staffId);

    List<StaffDTO> getAllStaff();

    void deleteStaff(int staffId);

    String getEmailById(int staffId);
}
