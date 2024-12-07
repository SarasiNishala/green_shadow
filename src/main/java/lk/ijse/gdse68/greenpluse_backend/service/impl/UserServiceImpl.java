package lk.ijse.gdse68.greenpluse_backend.service.impl;


import lk.ijse.gdse68.greenpluse_backend.dao.StaffDAO;
import lk.ijse.gdse68.greenpluse_backend.dao.UserDAO;
import lk.ijse.gdse68.greenpluse_backend.dto.UserDTO;
import lk.ijse.gdse68.greenpluse_backend.entity.UserEntity;
import lk.ijse.gdse68.greenpluse_backend.exception.NotFoundException;
import lk.ijse.gdse68.greenpluse_backend.service.UserService;
import lk.ijse.gdse68.greenpluse_backend.util.Mapping;
import lk.ijse.gdse68.greenpluse_backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public int saveUser(UserDTO userDTO) {
        System.out.println("save user");
        UserEntity id = checkNextId();
        int new_id = 0;
        if (id != null) {
            new_id = id.getUserId() + 1;
        }else {
            new_id = 1;
        }
        userDTO.setUserId(new_id);
        if (userDAO.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else if (staffDAO.existsByEmail(userDTO.getEmail())) {
            return VarList.Forbidden;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDAO.save(mapping.convertToUserEntity(userDTO));
            System.out.println("ok");
            return VarList.Created;
        }
    }

    private UserEntity checkNextId() {
        return userDAO.getLastId();
    }

    @Override
    public UserDTO loadUserDetailsByUsername(String email) {
        UserEntity user = userDAO.findByEmail(email);
        return mapping.convertToUserDTO(user);
    }

    @Override
    public UserDTO loadUserDetailsByUsername(String email, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = userDAO.findByEmail(email);
        System.out.println("1");
        if (userEntity == null) {
            System.out.println("null");
            return null;
        } else {
            System.out.println("2");
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                System.out.println(userEntity);
                return mapping.convertToUserDTO(userEntity);
            } else {
                System.out.println("3");
                return null;
            }
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                getAuthority(userEntity)
        );
    }

    @Override
    public void updateUser(UserDTO updateBuidUserDto) {
        Optional<UserEntity> tmpUserEntity = userDAO.findById(updateBuidUserDto.getUserId());
        if (!tmpUserEntity.isPresent()) {
            throw new NotFoundException("User Not Found");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            tmpUserEntity.get().setEmail(updateBuidUserDto.getEmail());
            tmpUserEntity.get().setPassword(passwordEncoder.encode(updateBuidUserDto.getPassword()));
            tmpUserEntity.get().setRole(updateBuidUserDto.getRole());
        }
    }

    @Override
    public void deleteUser(int userId) {
        Optional<UserEntity> tmpUserEntity = userDAO.findById(userId);
        if (!tmpUserEntity.isPresent()) {
            throw new NotFoundException("User Not Found");
        } else {
            userDAO.deleteById(userId);
        }
    }

    @Override
    public UserDTO getSelectedUser(int userId) {
        return mapping.convertToUserDTO(userDAO.getUserEntitiesByUserId(userId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.convertToUserDTOList(userDAO.findAll());
    }

    @Override
    public void updateUserEmailAndRole(String email, String role) {
        UserEntity tmpUserEntity = userDAO.findByEmail(email);
        if (tmpUserEntity == null) {
            throw new NotFoundException("User Not Found");
        } else {
            tmpUserEntity.setEmail(email);
            tmpUserEntity.setRole(role);
        }
    }

    @Override
    public void updateUserPassword(int id, String updatePassword) {
        Optional<UserEntity> tmpUserEntity = userDAO.findById(id);
        if (!tmpUserEntity.isPresent()) {
            throw new NotFoundException("User Not Found");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            tmpUserEntity.get().setPassword(passwordEncoder.encode(updatePassword));
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole())); // Prefix roles with 'ROLE_'
        return authorities;
    }
}
