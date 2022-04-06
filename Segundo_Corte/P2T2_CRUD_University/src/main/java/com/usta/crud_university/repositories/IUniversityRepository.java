package com.usta.crud_university.repositories;

import com.usta.crud_university.models.University;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that will be used to access the University table.
 * 
 * @author Carlos Páez
 */
public interface IUniversityRepository extends JpaRepository<University, Long> {

    /**
     * Return the total number of universities in the database.
     * 
     * @return The number of universities in the database.
     */
    @Query("SELECT COUNT(u) FROM University u")
    public Integer countTotalUniversityRecords();
}
