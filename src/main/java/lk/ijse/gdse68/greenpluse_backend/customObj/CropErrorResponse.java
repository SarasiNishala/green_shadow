package lk.ijse.gdse68.greenpluse_backend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropErrorResponse implements Serializable, CropResponse {
    private int errorCode;
    private String errorMessage;
}
