package lk.ijse.gdse68.greenpluse_backend.service;

import lk.ijse.gdse68.greenpluse_backend.customObj.CropResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);

    void updateCrop(CropDTO cropDTO);

    List<CropDTO> getAllCrops();

    CropResponse getSelectedCrop(int cropCode);

    void deleteCrop(int cropCode);
}
