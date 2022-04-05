package com.usta.crud_university.repositories;

import com.usta.crud_university.models.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the interface that will be used to access the Professor entity.
 * 
 * @author Carlos Páez
 */
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
}
