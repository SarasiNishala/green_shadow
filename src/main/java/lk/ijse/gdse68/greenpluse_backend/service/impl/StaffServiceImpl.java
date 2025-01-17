package lk.ijse.gdse68.greenpluse_backend.service.impl;


import lk.ijse.gdse68.greenpluse_backend.dao.StaffDAO;
import lk.ijse.gdse68.greenpluse_backend.dto.StaffDTO;
import lk.ijse.gdse68.greenpluse_backend.entity.StaffEntity;
import lk.ijse.gdse68.greenpluse_backend.exception.NotFoundException;
import lk.ijse.gdse68.greenpluse_backend.service.StaffService;
import lk.ijse.gdse68.greenpluse_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void addStaff(StaffDTO staffDTO) {
        staffDAO.save(mapping.convertToStaffEntity(staffDTO));
    }

    @Override
    public void updateSatff(StaffDTO staffDTO, int staffId) {
        Optional<StaffEntity> tmpEntity = staffDAO.findById(staffId);
        if (!tmpEntity.isPresent()) {
            throw new NotFoundException("Staff not found");
        }else {
            tmpEntity.get().setFirstName(staffDTO.getFirstName());
            tmpEntity.get().setLastName(staffDTO.getLastName());
            tmpEntity.get().setDesignation(staffDTO.getDesignation());
            tmpEntity.get().setGender(staffDTO.getGender());
            tmpEntity.get().setJoinedDate(staffDTO.getJoinedDate());
            tmpEntity.get().setDOB(staffDTO.getDOB());
            tmpEntity.get().setBuildingNo(staffDTO.getBuildingNo());
            tmpEntity.get().setLane(staffDTO.getLane());
            tmpEntity.get().setMainCity(staffDTO.getMainCity());
            tmpEntity.get().setMainState(staffDTO.getMainState());
            tmpEntity.get().setPostalCode(staffDTO.getPostalCode());
            tmpEntity.get().setContactNo(staffDTO.getContactNo());
            tmpEntity.get().setEmail(staffDTO.getEmail());
            tmpEntity.get().setRole(staffDTO.getRole());
        }
    }

    @Override
    public StaffDTO getSelectedStaff(int staffId) {
        return mapping.convertToStaffDTO(staffDAO.getStaffEntitiesByStaffId(staffId));
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.convertToStaffDTOList(staffDAO.findAll());
    }

    @Override
    public void deleteStaff(int staffId) {
        Optional<StaffEntity> tmpEntity = staffDAO.findById(staffId);
        if(!tmpEntity.isPresent()){
            throw new NotFoundException("Staff Not Found");
        }else {
            staffDAO.deleteById(staffId);
        }
    }

    @Override
    public String getEmailById(int staffId) {
        Optional<StaffEntity> staff = staffDAO.findById(staffId);
        if(!staff.isPresent()){
            return staff.get().getEmail();
        }else {
            throw new NotFoundException("Staff Not Found");
        }
    }
}
