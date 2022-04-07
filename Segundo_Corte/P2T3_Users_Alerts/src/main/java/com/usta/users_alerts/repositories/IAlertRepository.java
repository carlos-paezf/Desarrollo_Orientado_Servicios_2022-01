package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.AlertEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that defines the methods that will be used to access
 * the AlertEntity table.
 *
 * @author Carlos Páez
 */
public interface IAlertRepository extends JpaRepository<AlertEntity, Long> {
    /**
     * Return the number of records in the AlertEntity table.
     * 
     * @return The number of records in the AlertEntity table.
     */
    @Query("SELECT COUNT(a) FROM AlertEntity a")
    public Integer countTotalAlertRecords();
}
