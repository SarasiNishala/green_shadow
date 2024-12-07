package lk.ijse.gdse68.greenpluse_backend.dto;

import lk.ijse.gdse68.greenpluse_backend.customObj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDetailsDTO implements CropResponse {
    private int logCode;
    private String logDate;
    private String logDetails;
    private String  cropStatus;
    private String observedImage;
    private int staffId;
    private int fieldCode;
    private int cropCode;
}
