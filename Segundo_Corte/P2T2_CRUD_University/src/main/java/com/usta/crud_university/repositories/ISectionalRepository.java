package com.usta.crud_university.repositories;

import com.usta.crud_university.models.Sectional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the interface that will be used to access the Sectional table.
 * 
 * @author Carlos Páez
 */
public interface ISectionalRepository extends JpaRepository<Sectional, Long> {
}
