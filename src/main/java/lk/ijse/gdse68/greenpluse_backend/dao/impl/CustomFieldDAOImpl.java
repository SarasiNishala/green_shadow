package lk.ijse.gdse68.greenpluse_backend.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lk.ijse.gdse68.greenpluse_backend.dao.CustomFieldDAO;


public class CustomFieldDAOImpl implements CustomFieldDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getIdByName(String fieldName) {
        String sql = "SELECT fieldCode FROM field WHERE fieldName = ?";
        Query query = entityManager.createNativeQuery(sql);
        // Set the positional parameter (1 for the first ?)
        query.setParameter(1, fieldName);
        // Retrieve the result and cast it to an Integer
        return ((Number) query.getSingleResult()).intValue();
    }
}
