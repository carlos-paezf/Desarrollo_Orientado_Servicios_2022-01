package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.LoginEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that defines the methods that will be used to interact
 * with the database.
 *
 * @author Carlos Páez
 */
public interface ILoginRepository extends JpaRepository<LoginEntity, Long> {
    /**
     * Return the total number of login records in the database
     * 
     * @return The number of records in the LoginEntity table.
     */
    @Query("SELECT COUNT(l) FROM LoginEntity l")
    public Integer countTotalLoginRecords();
}
