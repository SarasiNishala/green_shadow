package lk.ijse.gdse68.greenpluse_backend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lk.ijse.gdse68.greenpluse_backend.customObj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private int fieldCode;
}
