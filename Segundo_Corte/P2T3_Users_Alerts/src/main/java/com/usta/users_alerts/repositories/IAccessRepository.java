package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.AccessEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that defines the methods that will be used to access
 * the data in the database.
 *
 * @author Carlos Páez
 */
public interface IAccessRepository extends JpaRepository<AccessEntity, Long> {
    /**
     * Return the total number of access records in the database.
     * 
     * @return The number of records in the AccessEntity table.
     */
    @Query("SELECT COUNT(a) FROM AccessEntity a")
    public Integer countTotalAccessRecords();
}
