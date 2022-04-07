package com.usta.users_alerts.repositories;

import com.usta.users_alerts.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that will be used by the service layer to interact with
 * the database.
 * 
 * @author Carlos Páez
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Return the number of records in the UserEntity table.
     * 
     * @return The number of records in the UserEntity table.
     */
    @Query("SELECT COUNT(u) FROM UserEntity u")
    public Integer countTotalUserRecords();
}
