package lk.ijse.gdse68.greenpluse_backend.dao;


import lk.ijse.gdse68.greenpluse_backend.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDAO extends JpaRepository<EquipmentEntity, Integer> {

}
