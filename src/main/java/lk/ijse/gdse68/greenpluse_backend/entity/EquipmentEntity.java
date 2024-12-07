package lk.ijse.gdse68.greenpluse_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;
    private String name;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffEntity staff;
}
