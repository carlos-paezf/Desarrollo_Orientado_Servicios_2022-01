package com.usta.crud_university.repositories;

import com.usta.crud_university.models.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the interface that will be used to access the Faculty table.
 * 
 * @author Carlos Páez
 */
public interface IFacultyRepository extends JpaRepository<Faculty, Long> {
}
