package lk.ijse.gdse68.greenpluse_backend.service;

import lk.ijse.gdse68.greenpluse_backend.customObj.FieldResponse;
import lk.ijse.gdse68.greenpluse_backend.dto.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    int getFieldID(String fieldName);

    void updateField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldResponse getSelectedField(int fieldCode);

    void deleteField(int fieldCode);
}
