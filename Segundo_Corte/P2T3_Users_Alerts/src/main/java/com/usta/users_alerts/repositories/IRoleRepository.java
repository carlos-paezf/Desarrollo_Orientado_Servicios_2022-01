package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.RoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the RoleRepository class. It extends JpaRepository class and
 * implements the JpaRepository interface.
 * 
 * @author Carlos Páez
 */
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    /**
     * This function returns the total number of records in the RoleEntity table
     * 
     * @return The count of all records in the RoleEntity table.
     */
    @Query("SELECT COUNT(r) FROM RoleEntity r")
    public Integer countTotalRolesRecords();
}
