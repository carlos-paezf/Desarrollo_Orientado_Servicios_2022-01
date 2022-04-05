package com.usta.crud_university.repositories;

import com.usta.crud_university.models.University;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the interface that will be used to access the University table.
 * 
 * @author Carlos Páez
 */
public interface IUniversityRepository extends JpaRepository<University, Long> {
}
