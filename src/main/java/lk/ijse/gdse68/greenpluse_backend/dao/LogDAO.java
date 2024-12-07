package lk.ijse.gdse68.greenpluse_backend.dao;


import lk.ijse.gdse68.greenpluse_backend.entity.CropDetailsEnttiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDAO extends JpaRepository<CropDetailsEnttiy, Integer>{

}
