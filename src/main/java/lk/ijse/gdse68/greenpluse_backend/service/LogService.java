package lk.ijse.gdse68.greenpluse_backend.service;

import lk.ijse.gdse68.greenpluse_backend.customObj.CropDetailsResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.CropDetailsDTO;

import java.util.List;

public interface LogService {
    void saveLog(CropDetailsDTO cropDetailsDTO);

    void updateLog(CropDetailsDTO cropDetailsDTO);

    List<CropDetailsDTO> getAllLogs();

    CropDetailsResponse getSelectedLog(int logCode);

    void deleteLog(int logCode);
}
