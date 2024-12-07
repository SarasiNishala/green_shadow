package lk.ijse.gdse68.greenpluse_backend.dao;

import lk.ijse.gdse68.greenpluse_backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    UserEntity getUserEntitiesByUserId(int userId);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM user where email=:email && password=:password", nativeQuery = true)
    UserEntity findByEmailAndPasswords(@Param("email")String email, @Param("password")String password);

    @Query(value = "SELECT * FROM user  ORDER BY userId DESC LIMIT 1", nativeQuery = true)
    UserEntity getLastId();

    UserEntity findByEmail(String email);
}
