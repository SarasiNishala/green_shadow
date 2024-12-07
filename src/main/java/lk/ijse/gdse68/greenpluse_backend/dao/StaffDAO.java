package lk.ijse.gdse68.greenpluse_backend.dao;


import lk.ijse.gdse68.greenpluse_backend.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDAO extends JpaRepository<StaffEntity, Integer> {

    StaffEntity getStaffEntitiesByStaffId(int staffId);

    boolean existsByEmail(String email);

    StaffEntity findByEmail(String email);
}
