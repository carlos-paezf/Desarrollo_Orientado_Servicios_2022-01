package com.usta.p2t5_jwt_db.repositories;

import java.util.Optional;

import com.usta.p2t5_jwt_db.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Carlos Páez
 */
public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Find all UserEntity objects where the username field is equal to the value of
     * the first parameter passed to the function.
     * 
     * @param username The username of the user you want to find.
     * @return A UserEntity object
     */
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    public UserEntity findByUsername(String username);
}
