package com.usta.crud_university.repositories;

import com.usta.crud_university.models.Sectional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the interface that will be used to access the Sectional table.
 * 
 * @author Carlos Páez
 */
public interface ISectionalRepository extends JpaRepository<Sectional, Long> {
    /**
     * Return the total number of Sectional records in the database.
     * 
     * @return The number of Sectional records in the database.
     */
    @Query("SELECT COUNT(s) FROM Sectional s")
    public Integer countTotalSectionalRecords();
}
