package lk.ijse.gdse68.greenpluse_backend.dao;


import lk.ijse.gdse68.greenpluse_backend.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDAO extends JpaRepository<CropEntity, Integer> {
}
